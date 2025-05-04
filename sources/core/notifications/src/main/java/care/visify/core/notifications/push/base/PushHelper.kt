package care.visify.core.notifications.push.base

import android.app.Activity
import android.util.Log
import androidx.annotation.MainThread
import care.visify.api.models.notifications.TokenRequest
import care.visify.core.bus.EventBus
import care.visify.core.bus.subscribe
import care.visify.core.ktx.CoroutineDispatchers
import care.visify.core.repository.Parameters
import care.visify.core.repository.wrapSafeCall
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PushHelper @Inject constructor(
    private val bus: EventBus,
    private val pushManager: PushManager,
    private val dispatchers: CoroutineDispatchers,
    notificationService: NotificationService,
) {

    private data class TokenParameters(val token: String): Parameters

    private val registerCall = wrapSafeCall<Unit, TokenParameters>(
        call = { notificationService.registerFcmToken(TokenRequest(it.token)) }
    )

    private val unregisterCall = wrapSafeCall<Unit, TokenParameters>(
        call = { notificationService.unregisterFcmToken(TokenRequest(it.token)) }
    )

    private var isStarted: Boolean = false

    private val handler = CoroutineExceptionHandler { _, throwable ->
        Log.e("NotificationHelper", "Exception in context", throwable)
    }
    private val scope by lazy { CoroutineScope(Job() + handler) }

    @MainThread
    fun start(activity: Activity) {
        if (isStarted) return
        if (!pushManager.isGoogleServicesAvailable(activity)) {
            scope.launch {
                val succeed = pushManager.makeGoogleServicesAvailable(activity)
                if (succeed) startInternal()
            }
        } else startInternal()
    }

    fun forceRegister() = registerTokenInternal()

    suspend fun stop(): Unit = withContext(dispatchers.io) {
        val token = pushManager.registerDeviceToken()
        pushManager.unregisterDeviceToken()
        unregisterCall.load(TokenParameters(token))
    }

    private fun startInternal() {
        isStarted = true
        scope.launch(dispatchers.io) {
            bus.events.filterIsInstance<care.visify.core.bus.OnAuthSucceed>()
                .subscribe { registerTokenInternal() }
        }
        registerTokenInternal()
    }

    private fun registerTokenInternal() {
        scope.launch(dispatchers.io) {
            val token = pushManager.registerDeviceToken()
            registerCall.load(TokenParameters(token))
        }
    }
}