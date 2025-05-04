package care.visify.core.api.sse.engine.handler

interface SseEventHandler {
    fun handleEvent(payload: String)
}