package care.visify.business.core.auth.logout

import care.visify.core.notifications.push.base.PushHelper
import care.visify.core.pref.PreferencesFactory
import care.visify.feature.auth.api.OnLogoutCallback
import javax.inject.Inject


class BusinessOnLogoutCallback @Inject constructor(
    private val preferencesFactory: PreferencesFactory,
    private val pushHelper: PushHelper,
) : OnLogoutCallback {

    override suspend fun preLogoutActions() {
        runCatching {
            pushHelper.stop()
        }
    }

    override suspend fun postLogoutActions() {
        runCatching {
            preferencesFactory.clearAll()
        }
    }
}