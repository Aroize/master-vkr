package care.visify.core.notifications.intent

import android.content.Intent

interface IntentHandler {
    fun handle(intent: Intent?) : Boolean
    fun handleInitial(intent: Intent) : Boolean
}