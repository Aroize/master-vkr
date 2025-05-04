package care.visify.core.notifications.intent

import android.app.PendingIntent
import care.visify.core.api.sse.ConnectionEvent

interface PendingIntentProvider {
    fun provideForEvent(connectionEvent: ConnectionEvent): PendingIntent
    fun provideForDevTools(): PendingIntent
}