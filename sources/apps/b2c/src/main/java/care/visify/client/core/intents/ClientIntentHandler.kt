package care.visify.client.core.intents

import android.content.Intent
import care.visify.core.navigator.api.VisifyRouter
import care.visify.core.notifications.intent.CommonIntentArgs
import care.visify.core.notifications.intent.IntentHandler
import care.visify.feature.debug.api.DebugPanelScreen
import care.visify.feature.im.api.Peer
import care.visify.feature.im.api.screen.ChatScreen
import care.visify.feature.im.api.screen.ClientChatListScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class ClientIntentHandler @Inject constructor(
    private val scope: CoroutineScope,
    private val router: VisifyRouter,
) : IntentHandler {
    override fun handle(intent: Intent?): Boolean =
        when (intent?.getStringExtra(CommonIntentArgs.GLOBAL_ARG)) {

            CommonIntentArgs.ARG_DEBUG_PANEL -> {
                handleDebugIntent(intent)
                true
            }

            CommonIntentArgs.ARG_CHAT_ID -> {
                handleChatIntent(intent)
                true
            }

            else -> false
        }

    private fun handleChatIntent(intent: Intent) {
        val maybeChatId = intent.getIntExtra(CommonIntentArgs.ARG_ORG_ID, -1)
        if (maybeChatId > 0) {
            scope.launch {
                router.navigateTo(ChatScreen(Peer.Org(maybeChatId)))
            }
        }
    }

    private fun handleDebugIntent(intent: Intent) {
        if (intent.getBooleanExtra(CommonIntentArgs.ARG_DEBUG_PANEL, false)) {
            scope.launch {
                router.navigateTo(DebugPanelScreen)
            }
        }
    }

    override fun handleInitial(intent: Intent): Boolean {
        val maybeChatId = intent.getIntExtra(CommonIntentArgs.ARG_ORG_ID, -1)
        if (maybeChatId > 0) {
            scope.launch {
                router.newRootChain(
                    ClientChatListScreen,
                    ChatScreen(Peer.Org(maybeChatId))
                )
            }
            return true
        }
        return false
    }
}