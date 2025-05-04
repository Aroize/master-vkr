package care.visify.core.notifications.push.gms

import android.app.Activity
import android.content.Context
import android.util.Log
import care.visify.core.notifications.push.base.PushManager
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.firebase.messaging.FirebaseMessaging
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FirebasePushManager @Inject constructor() : PushManager {

    override fun isGoogleServicesAvailable(context: Context): Boolean {
        return GoogleApiAvailability.getInstance()
            .isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS
    }

    override suspend fun makeGoogleServicesAvailable(activity: Activity): Boolean {
        return suspendCoroutine { continuation ->
            GoogleApiAvailability.getInstance()
                .makeGooglePlayServicesAvailable(activity)
                .addOnSuccessListener {
                    Log.i(TAG, "Succeed making google services available")
                    continuation.resume(true)
                }
                .addOnFailureListener {
                    Log.e(TAG, "Failed making google services available", it)
                    continuation.resume(false)
                }
        }
    }

    override suspend fun registerDeviceToken(): String {
        return suspendCoroutine { continuation ->
            val instance = FirebaseMessaging.getInstance()
            instance.token
                .addOnSuccessListener { token ->
                    Log.i(TAG, "Fcm registered token = $token")
                    continuation.resume(token)
                }
                .addOnFailureListener {
                    Log.e(TAG, "Fcm fetching token failed", it)
                    continuation.resumeWithException(it)
                }
        }
    }

    override fun unregisterDeviceToken() {
        FirebaseMessaging.getInstance().deleteToken()
            .addOnSuccessListener {
                Log.i(TAG, "Fcm token deletion succeed")
            }
            .addOnFailureListener {
                Log.e(TAG, "Fcm token deletion failed")
            }
    }

    companion object {
        private const val TAG = "FirebaseNotificationManager"
    }
}