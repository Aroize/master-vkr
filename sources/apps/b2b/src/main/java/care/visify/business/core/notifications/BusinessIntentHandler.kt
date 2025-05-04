package care.visify.business.core.notifications

import android.content.Intent
import care.visify.core.navigator.api.VisifyRouter
import care.visify.core.notifications.intent.CommonIntentArgs
import care.visify.core.notifications.intent.IntentHandler
import care.visify.feature.debug.api.DebugPanelScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class BusinessIntentHandler @Inject constructor(
    private val scope: CoroutineScope,
    private val router: VisifyRouter,
) : IntentHandler {
    override fun handle(intent: Intent?): Boolean =
        when (intent?.getStringExtra(CommonIntentArgs.GLOBAL_ARG)) {

            CommonIntentArgs.ARG_DEBUG_PANEL -> {
                handleDebugIntent(intent)
                true
            }

            else -> false
        }

    private fun handleDebugIntent(intent: Intent) {
        if (intent.getBooleanExtra(CommonIntentArgs.ARG_DEBUG_PANEL, false)) {
            scope.launch {
                router.navigateTo(DebugPanelScreen)
            }
        }
    }

    override fun handleInitial(intent: Intent): Boolean = false
}