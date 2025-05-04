package care.visify.business.main.state

import android.app.Activity
import android.content.Intent
import care.visify.core.arch.VisifyEffect
import care.visify.core.arch.VisifyIntent
import care.visify.core.arch.VisifyState

data object BusinessState : VisifyState

sealed interface BusinessIntent : VisifyIntent {
    data object LoadData: BusinessIntent
    data class HandleAndroidOnNewIntent(val intent: Intent?) : BusinessIntent
    data class InitDevTools(val activity: Activity) : BusinessIntent
}

sealed interface BusinessEffect : VisifyEffect