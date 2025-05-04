package care.visify.core.api.sse.engine

interface ServerConnectionEngine {
    val isRunning: Boolean
    fun start()
    fun stop()
}