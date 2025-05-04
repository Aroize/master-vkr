package care.visify.core.notifications.push.base

sealed interface RemoteEvent {
    enum class Type(val value: String) {
        PUSH("push"),
        SSE("sse")
        ;
    }

    val type: Type

    data class PushEvent(val title: String, val text: String) : RemoteEvent {
        override val type: Type = Type.PUSH
    }

    data class SseEvent(val data: String): RemoteEvent {
        override val type: Type = Type.SSE
    }
}