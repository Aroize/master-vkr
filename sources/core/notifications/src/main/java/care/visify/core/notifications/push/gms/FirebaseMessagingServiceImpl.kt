package care.visify.core.notifications.push.gms

import android.util.Log
import care.visify.core.api.sse.ConnectionEvent
import care.visify.core.api.sse.engine.handler.EventWithId
import care.visify.core.api.sse.engine.handler.EventWithIdParser
import care.visify.core.api.sse.engine.handler.SseEventHandler
import care.visify.core.notifications.push.base.PushHelper
import care.visify.core.notifications.showing.NotificationShowingProxy
import care.visify.core.notifications.showing.VisifyNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


// fixme(notifications)
@AndroidEntryPoint
class FirebaseMessagingServiceImpl : FirebaseMessagingService() {

    @Inject
    lateinit var gson: Gson

    @Inject
    lateinit var eventWithIdParser: EventWithIdParser

    @Inject
    lateinit var pushHelper: PushHelper
    @Inject
    lateinit var sseEventHandler: SseEventHandler
    @Inject
    lateinit var notificationShower: NotificationShowingProxy

    override fun onMessageReceived(message: RemoteMessage) {
        Log.d(TAG, "New FCM message = $message")
        runCatching {
            message.notification?.let { notification ->
                val payload = message.data["payload"] ?: return@let
                val title = notification.title ?: "Неизвестно"
                val body = notification.body ?: "Неизвестно"
                val event = payloadToEvent(payload)
                val push = VisifyNotification.PushMessage(
                    title = title,
                    content = body,
                    payload = event
                )
                notificationShower.sendNotification(push)
            }
        }.onFailure {
            Log.e(TAG, "Failed to proceed message; data = ${message.data}")
        }
    }

    override fun onNewToken(token: String) {
        Log.d(TAG, "onNewToken provided")
        pushHelper.forceRegister()
    }

    private fun payloadToEvent(payload: String?): ConnectionEvent {
        payload ?: throw IllegalArgumentException("Payload can't be null or empty")
        val eventWithId = gson.fromJson(payload, EventWithId::class.java)
        return eventWithIdParser.parse(eventWithId)
    }

    private companion object {
        const val TAG = "FirebaseMessagingServiceImpl"
    }
}