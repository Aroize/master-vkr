package care.visify.core.api.sse.engine

internal interface SseConnectionRunnable: Runnable {
    val isRunning: Boolean
    fun cancel()
}