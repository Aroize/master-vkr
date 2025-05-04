package care.visify.core.api.sse.engine.handler

class EventWithId(
    val id: Long,
    val type: String,
    val event: String
)