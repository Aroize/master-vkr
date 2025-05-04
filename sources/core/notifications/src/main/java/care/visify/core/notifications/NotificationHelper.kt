package care.visify.core.notifications

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import care.visify.core.notifications.intent.PendingIntentProvider
import care.visify.core.notifications.showing.VisifyNotification
import care.visify.core.util.atLeastTiramisu
import care.visify.ui.icons.IconsR
import javax.inject.Inject

class NotificationHelper @Inject constructor(
    private val context: Context,
    private val pendingIntentProvider: PendingIntentProvider,
) {

    private val notificationManager: NotificationManagerCompat
        get() = NotificationManagerCompat.from(context)

    fun init() {
        createChannelIfNeeded()
    }

    fun areNotificationsEnabled(): Boolean = notificationManager.areNotificationsEnabled()

    @SuppressLint("MissingPermission")
    fun showPushNotification(visifyNotification: VisifyNotification) {
        atLeastTiramisu(
            action = { if (hasPostPermission().not()) return },
            fallback = { }
        )

        if (areNotificationsEnabled().not()) return
        createChannelIfNeeded()

        val notification = visifyNotification.toNotification()
        with(NotificationManagerCompat.from(context)) {
            val notificationId = getNotificationId(visifyNotification)
            notify(notificationId, notification)
        }
    }

    fun cancelPushNotification(visifyNotification: VisifyNotification) {
        notificationManager.cancel(getNotificationId(visifyNotification))
    }

    private fun getNotificationId(visifyNotification: VisifyNotification): Int {
        return visifyNotification.hashCode()
    }

    private fun createChannelIfNeeded() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return
        val name = "Уведомления" // todo()
        val descriptionText = "Канал уведомлений приложения Visify" // todo()
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        notificationManager.createNotificationChannel(channel)
    }

    // fixme(notifications)
    private fun VisifyNotification.toNotification(): Notification {
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(IconsR.ic_status_bar_24)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        when (val notification = this@toNotification) {
            is VisifyNotification.PushMessage -> {
                builder.setContentIntent(pendingIntentProvider.provideForEvent(notification.payload))
                builder.setContentTitle(notification.title)
                builder.setContentText(notification.content)
            }

            is VisifyNotification.DevToolsNotification -> {
                builder.setContentIntent(pendingIntentProvider.provideForDevTools())
                builder.setContentTitle(notification.title)
                builder.setSilent(true)
                builder.setContentText(notification.content)
                builder.setOngoing(false)
            }
        }
        return builder.build()
    }


    @TargetApi(Build.VERSION_CODES.TIRAMISU)
    private fun hasPostPermission(): Boolean {
        return context.checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        private const val CHANNEL_ID = "VisifyClientChannel"
    }
}