package care.visify.client.core.intents

import android.content.Context
import android.content.Intent
import care.visify.client.main.MainActivity
import care.visify.core.notifications.intent.IntentProvider
import javax.inject.Inject

class ClientIntentProvider @Inject constructor() : IntentProvider {
    override fun createActivityIntent(context: Context): Intent =
        Intent(context, MainActivity::class.java)
}