package care.visify.core.notifications.intent

import android.content.Context
import android.content.Intent

interface IntentProvider {
    fun createActivityIntent(context: Context): Intent
}