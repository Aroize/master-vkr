package care.visify.business.core.notifications

import android.content.Context
import android.content.Intent
import care.visify.business.main.MainActivity
import care.visify.core.notifications.intent.IntentProvider
import javax.inject.Inject

class BusinessIntentProvider @Inject constructor() : IntentProvider {
    override fun createActivityIntent(context: Context): Intent =
        Intent(context, MainActivity::class.java)
}