package care.visify.core.api.sse.engine.handler

import care.visify.core.api.sse.ConnectionEvent

interface EventWithIdParser {
    fun parse(withId: EventWithId): ConnectionEvent
}