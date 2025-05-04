package care.visify.core.notifications.showing

import care.visify.core.api.sse.ConnectionEvent

sealed interface VisifyNotification {

    val title: String
    val content: String

    data class PushMessage(
        override val title: String,
        override val content: String,
        val payload: ConnectionEvent
    ): VisifyNotification

    data class DevToolsNotification(
        override val title: String,
        override val content: String
    ) : VisifyNotification
}