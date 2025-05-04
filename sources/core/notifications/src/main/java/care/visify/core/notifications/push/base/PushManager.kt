package care.visify.core.notifications.push.base

import android.app.Activity
import android.content.Context

interface PushManager {
    fun isGoogleServicesAvailable(context: Context): Boolean

    suspend fun makeGoogleServicesAvailable(activity: Activity): Boolean
    suspend fun registerDeviceToken(): String
    fun unregisterDeviceToken()
}