package care.visify.core.notifications.intent

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import care.visify.core.api.sse.ConnectionEvent
import care.visify.core.api.sse.NewMessageEvent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

// fixme(notifications)
class PendingIntentProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val intentProvider: IntentProvider
) : PendingIntentProvider {

    override fun provideForEvent(connectionEvent: ConnectionEvent): PendingIntent {
        return when (connectionEvent) {
            is NewMessageEvent -> provideForChat(connectionEvent)
            else -> provideForStartUp()
        }
    }

    override fun provideForDevTools(): PendingIntent {
        val intent = createIntentWithDebugPanel(context)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        return PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun provideForStartUp(): PendingIntent {
        val intent = createIntent(context)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        return PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun provideForChat(event: NewMessageEvent): PendingIntent {
        val intent = createIntentWithChat(context, event.clientId, event.orgId)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        return PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun createIntent(ctx: Context): Intent = intentProvider.createActivityIntent(ctx)

    private fun createIntentWithChat(
        ctx: Context,
        clientId: Int,
        orgId: Int
    ) = createIntent(ctx)
        .apply { putExtra(CommonIntentArgs.GLOBAL_ARG, CommonIntentArgs.ARG_CHAT_ID) }
        .apply { putExtra(CommonIntentArgs.ARG_CLIENT_ID, clientId) }
        .apply { putExtra(CommonIntentArgs.ARG_ORG_ID, orgId) }

    private fun createIntentWithDebugPanel(ctx: Context) = createIntent(ctx)
        .apply { putExtra(CommonIntentArgs.GLOBAL_ARG, CommonIntentArgs.ARG_DEBUG_PANEL) }
        .apply { putExtra(CommonIntentArgs.ARG_DEBUG_PANEL, true) }
}