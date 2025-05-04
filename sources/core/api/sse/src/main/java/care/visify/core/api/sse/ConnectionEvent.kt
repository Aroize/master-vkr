package care.visify.core.api.sse

import care.visify.core.bus.Event


sealed interface ConnectionEvent : Event {

    enum class Type(val type: String) {
        DEBUG("DEBUG"),
        NEW_MSG("CHAT_MESSAGE"),
        CHAT_READ_UNTIL("CHAT_READ_UNTIL"),
        UPD_COUNTERS("CHAT_UNREAD_COUNTERS"),
        FAKE_CHAT_ARCHIVED("FAKE_CHAT_ARCHIVED"),
        FAKE_CHAT_CREATED("FAKE_CHAT_CREATED"),
        FAKE_CHAT_GETS_RESPONSE("FAKE_CHAT_GETS_RESPONSE"),
        FAKE_CHAT_READ("FAKE_CHAT_READ"),
        ONE_SHOT_TASK_OPENED("ONE_SHOT_TASK_OPENED"),
        ONE_SHOT_TASK_VISIBILITY("ONE_SHOT_TASK_VISIBILITY"),
        ONE_SHOT_TASK_CLOSED("ONE_SHOT_TASK_CLOSED"),
        TASK_COUNTERS("TASK_COUNTERS")
        ;

        companion object {
            fun forName(value: String) = entries.first { it.type == value }
        }
    }
}

// ============ begin region

data class Plain(val value: String) : ConnectionEvent

data class NewMessageEvent(
    val clientId: Int,
    val orgId: Int,
    val cnvId: Long,
    val msgId: Int
) : ConnectionEvent

data class UpdateUnreadCountersEvent(
    val chats: Int,
    val responses: Int
) : ConnectionEvent


class ChatReadUntilEvent(
    val clientId: Int,
    val orgId: Int,
    val untilCnvId: Long,
) : ConnectionEvent

// fixme(notifications)
data class FakeChatArchivedEvent(
    val clientId: Int,
    val orgId: Int,
    val orderId: Int
) : ConnectionEvent

// fixme(notifications)
data class FakeChatCreatedEvent(
    val clientId: Int,
    val orgId: Int,
    val orderId: Int
) : ConnectionEvent

// fixme(notifications)
data class FakeChatGetsResponseEvent(
    val clientId: Int,
    val orgId: Int,
    val orderId: Int
) : ConnectionEvent

// fixme(notifications)
data class FakeChatReadEvent(
    val clientId: Int,
    val orgId: Int,
    val orderId: Int
) : ConnectionEvent

data class OneShotTaskOpenedEvent(
    val targetId: Int, // orgId if event send to employee, userId if event sent to client
    val taskId: Int
) : ConnectionEvent

data class OneShotTaskVisibilityEvent(
    val targetId: Int,
    val taskId: Int,
    val isHidden: Boolean
) : ConnectionEvent

data class OneShotTaskClosedEvent(
    val targetId: Int,
    val taskId: Int
) : ConnectionEvent

data class TaskCountersEvent(
    val userId: Int,
    val total: Int,
    val visitPhoto: Int?,
    val newReview: Int?,
    val orderStatusChanged: Int?,
    val newMarketOrder: Int?,
    val hidden: Int,
) : ConnectionEvent

// ============ end region

