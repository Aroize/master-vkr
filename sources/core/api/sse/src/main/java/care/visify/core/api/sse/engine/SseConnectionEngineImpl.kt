package care.visify.core.api.sse.engine

import android.util.Log
import care.visify.core.api.sse.engine.handler.SseEventHandler
import care.visify.core.api.sse.service.ConnectionService
import care.visify.core.bus.NetworkStateEvent
import care.visify.core.bus.OnAuthSucceed
import care.visify.core.bus.subscribe
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.runBlocking
import java.net.SocketTimeoutException
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.Executors
import java.util.concurrent.Future
import javax.inject.Inject

class SseConnectionEngineImpl @Inject constructor(
    private val service: ConnectionService,
    private val eventBus: care.visify.core.bus.EventBus,
    private val eventHandler: SseEventHandler,
): ServerConnectionEngine {

    private val exceptionFilter: (Throwable) -> Boolean = { it is SocketTimeoutException }

    private val executor = Executors.newFixedThreadPool(THREAD_COUNT)

    @Volatile
    private var _isRunning: Boolean = false

    private var authListenerFuture: Future<*>? = null
    private var networkListenerFuture: Future<*>? = null

    private var actualRunner: SseConnectionRunnable? = null

    private val failuresCollection = ConcurrentHashMap<Class<*>, Int>()

    override val isRunning: Boolean
        get() = _isRunning || actualRunner?.isRunning == true

    override fun start() {
        Log.i(TAG, "Trying to startup SSE engine")

        if (isRunning) {
            Log.i(TAG, "SSE Engine is already started, nothing to do")
            return
        }

        if (_isRunning.not()) {
            synchronized(this) {
                if (_isRunning) return
                _isRunning = true
            }
        }

        startConnection()
        observeSideEffects()
    }


    private fun observeSideEffects() {
        if (authListenerFuture?.isDone == true) {
            authListenerFuture = executor.submit {
                runBlocking {
                    eventBus.events
                        .filterIsInstance<OnAuthSucceed>()
                        .distinctUntilChanged { _, _ -> false }
                        .subscribe {
                            startConnection()
                        }
                }
            }
        }
        if (networkListenerFuture?.isDone == true) {
            networkListenerFuture = executor.submit {
                runBlocking {
                    eventBus.events
                        .filterIsInstance<NetworkStateEvent>()
                        .filter { it.isNetworkAvailable }
                        .distinctUntilChanged()
                        .subscribe {
                            if (actualRunner?.isRunning == false) {
                                startConnection()
                            }
                        }
                }
            }
        }
    }

    private fun startConnection() {
        Log.d(TAG, "Starting connection to server | runner = ${actualRunner?.isRunning}")
        actualRunner?.cancel()
        actualRunner = SseConnectionRunnableImpl(
            service = service,
            onEvent = ::handleConnectionEvent,
            onException = ::handleException,
            onStop = { gracefully ->
                Log.d(TAG, "SSE Connection has stopped; gracefully = $gracefully")
                failuresCollection.clear()
                if (gracefully) {
                    startConnection()
                }
            }
        )
        executor.submit(actualRunner)
    }

    override fun stop() {
        Log.i(TAG, "SSE Engine stopping; failures = [${failuresCollection.size}] runner = [${actualRunner?.isRunning}]")
        failuresCollection.clear()
        actualRunner?.cancel()
        actualRunner = null
        _isRunning = false
    }

    private fun handleConnectionEvent(payload: String) {
        Log.i(TAG, "SSE handled event = $payload")
        eventHandler.handleEvent(payload = payload)
    }

    private fun handleException(exc: Throwable) {
        Log.e(TAG, "SSE Handled exception", exc)
        if (exceptionFilter(exc)) {
            Log.i(TAG, "Exception passed filter | restarting")
            startConnection()
            return
        }
        val failures = failuresCollection.getOrPut(exc::class.java) { 0 } + 1
        Log.e(TAG, "SSE Failed times = $failures")
        failuresCollection[exc::class.java] = failures
        if (failures >= FAILURES_THRESHOLD) {
            stop()
            return
        }
        startConnection()
    }

    companion object {

        private const val FAILURES_THRESHOLD = 5

        private const val THREAD_COUNT = 8

        private const val TAG = "SseConnectionEngine"
    }
}