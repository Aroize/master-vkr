package care.visify.core.api.sse.engine.handler

import care.visify.core.api.sse.ChatReadUntilEvent
import care.visify.core.api.sse.ConnectionEvent
import care.visify.core.api.sse.FakeChatArchivedEvent
import care.visify.core.api.sse.FakeChatCreatedEvent
import care.visify.core.api.sse.FakeChatGetsResponseEvent
import care.visify.core.api.sse.FakeChatReadEvent
import care.visify.core.api.sse.NewMessageEvent
import care.visify.core.api.sse.OneShotTaskClosedEvent
import care.visify.core.api.sse.OneShotTaskOpenedEvent
import care.visify.core.api.sse.OneShotTaskVisibilityEvent
import care.visify.core.api.sse.Plain
import care.visify.core.api.sse.TaskCountersEvent
import care.visify.core.api.sse.UpdateUnreadCountersEvent
import com.google.gson.Gson
import javax.inject.Inject

internal class EventWithIdParserImpl @Inject constructor(
    private val gson: Gson,
) : EventWithIdParser {
    override fun parse(withId: EventWithId): ConnectionEvent {
        return when (ConnectionEvent.Type.forName(withId.type)) {
            ConnectionEvent.Type.DEBUG -> gson.fromJson(withId.event, Plain::class.java)
            ConnectionEvent.Type.NEW_MSG -> gson.fromJson(withId.event, NewMessageEvent::class.java)
            ConnectionEvent.Type.UPD_COUNTERS -> gson.fromJson(withId.event, UpdateUnreadCountersEvent::class.java)
            ConnectionEvent.Type.CHAT_READ_UNTIL -> gson.fromJson(withId.event, ChatReadUntilEvent::class.java)
            ConnectionEvent.Type.FAKE_CHAT_ARCHIVED -> gson.fromJson(withId.event, FakeChatArchivedEvent::class.java)
            ConnectionEvent.Type.FAKE_CHAT_CREATED -> gson.fromJson(withId.event, FakeChatCreatedEvent::class.java)
            ConnectionEvent.Type.FAKE_CHAT_GETS_RESPONSE -> gson.fromJson(withId.event, FakeChatGetsResponseEvent::class.java)
            ConnectionEvent.Type.FAKE_CHAT_READ -> gson.fromJson(withId.event, FakeChatReadEvent::class.java)
            ConnectionEvent.Type.ONE_SHOT_TASK_OPENED -> gson.fromJson(withId.event, OneShotTaskOpenedEvent::class.java)
            ConnectionEvent.Type.ONE_SHOT_TASK_VISIBILITY -> gson.fromJson(withId.event, OneShotTaskVisibilityEvent::class.java)
            ConnectionEvent.Type.ONE_SHOT_TASK_CLOSED -> gson.fromJson(withId.event, OneShotTaskClosedEvent::class.java)
            ConnectionEvent.Type.TASK_COUNTERS -> gson.fromJson(withId.event, TaskCountersEvent::class.java)
        }
    }
}