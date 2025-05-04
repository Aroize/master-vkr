package care.visify.client.feature.auth

import android.os.Handler
import android.os.Looper
import care.visify.core.api.http.auth.AppAuthListener
import care.visify.core.bus.EventBus
import care.visify.core.bus.OnAuthSucceed
import care.visify.core.ktx.CoroutineDispatchers
import care.visify.core.navigator.api.VisifyNavAnimation
import care.visify.core.navigator.api.VisifyRouter
import care.visify.feature.auth.api.screens.EmailOtpScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ClientAuthListener @Inject constructor(
    private val bus: EventBus,
    private val visifyRouter: VisifyRouter,
    private val dispatchers: CoroutineDispatchers,
) : AppAuthListener, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatchers.io

    private val handler by lazy { Handler(Looper.getMainLooper()) }

    override fun onAuthFailed() {
        handler.post {
            launch {
                visifyRouter.navigateTo(
                    EmailOtpScreen(isAuthFailure = true),
                    VisifyNavAnimation.SlideBottomTop
                )
            }
        }
    }

    override fun onAuthSucceed() {
        launch { bus.publish(OnAuthSucceed) }
    }
}