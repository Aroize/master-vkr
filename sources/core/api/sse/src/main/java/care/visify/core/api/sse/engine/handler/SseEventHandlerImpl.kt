package care.visify.core.api.sse.engine.handler

import android.util.Log
import care.visify.core.api.sse.repository.RemoteEventRepository
import care.visify.core.bus.EventBus
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okio.withLock
import java.util.concurrent.Executors
import java.util.concurrent.locks.ReentrantLock
import javax.inject.Inject

class SseEventHandlerImpl @Inject constructor(
    private val gson: Gson,
    private val eventBus: EventBus,
    private val eventWithIdParser: EventWithIdParser,
    private val remoteEventRepository: RemoteEventRepository
) : SseEventHandler {

    private val lock = ReentrantLock()
    private val executor = Executors.newFixedThreadPool(8)

    override fun handleEvent(payload: String) {
        executor.submit { handleEventInternal(payload) }
    }

    private fun handleEventInternal(payload: String) = runCatching {
        val eventWithId = gson.fromJson(payload, EventWithId::class.java)
        lock.withLock {
            if (remoteEventRepository.checkExists(eventWithId.id)) {
                Log.i("SseEventHandler", "Event with id [${eventWithId.id}] already handled")
                return@runCatching
            }
            remoteEventRepository.create(eventWithId.id)
        }

        val event = eventWithIdParser.parse(eventWithId)
        runBlocking { eventBus.publish(event) }
    }.onFailure { exc ->
        Log.e(TAG, "Failure during parsing event", exc)
    }

    companion object {
        private const val TAG = "EventHandler"
    }
}