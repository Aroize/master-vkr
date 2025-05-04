package care.visify.core.api.sse.engine

import android.util.Log
import care.visify.core.api.sse.service.ConnectionService
import care.visify.core.api.sse.service.SseCall
import okhttp3.internal.closeQuietly
import retrofit2.HttpException
import java.io.BufferedReader

internal class SseConnectionRunnableImpl(
    private val service: ConnectionService,
    private val onEvent: (String) -> Unit,
    private val onException: (Throwable) -> Unit,
    private val onStop: (Boolean) -> Unit
) : SseConnectionRunnable {

    @Volatile
    private var _isRunning: Boolean = true

    private var request: SseCall? = null
    private var reader: BufferedReader? = null

    override val isRunning: Boolean
        get() = _isRunning


    override fun run() {
        runCatching {
            request = service.connect()
            request?.let { call ->
                val response = call.execute()
                if (response.isSuccessful.not()) throw HttpException(response)
                reader = response.body()?.byteStream()?.bufferedReader()
                    ?: throw IllegalStateException()

                while (Thread.interrupted().not() && isRunning) {
                    val input = reader?.readLine() ?: continue
                    if (input.isBlank()) continue
                    when {
                        input.startsWith(DATA_TAG) -> processData(input.content(DATA_TAG))
                        input.startsWith(ID_TAG) -> processId(input.content(ID_TAG))
                        input.startsWith(EVENT_TAG) -> processEvent(input.content(EVENT_TAG))
                    }
                }
            }
        }
            .fold(
                onSuccess = {
                    request?.cancel()
                    reader?.closeQuietly()
                    onStop(isRunning)
                },
                onFailure = {
                    request?.cancel()
                    reader?.closeQuietly()
                    onException(it)
                }
            )
    }

    private fun processData(data: String) = onEvent(data)
    private fun processId(id: String) = Unit
    private fun processEvent(event: String) = Unit

    private fun String.content(tag: String) = substring(tag.length)

    override fun cancel() {
        _isRunning = false
    }

    companion object {
        private const val ID_TAG = "id:"
        private const val EVENT_TAG = "event:"
        private const val DATA_TAG = "data:"
    }
}