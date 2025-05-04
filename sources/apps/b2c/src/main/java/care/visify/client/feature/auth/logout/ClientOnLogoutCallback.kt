package care.visify.client.feature.auth.logout

import care.visify.core.notifications.push.base.PushHelper
import care.visify.core.pref.PreferencesFactory
import care.visify.feature.auth.api.OnLogoutCallback
import care.visify.feature.catalog.api.repository.SearchRepository
import javax.inject.Inject

class ClientOnLogoutCallback @Inject constructor(
    private val searchRepository: SearchRepository,
    private val pendingOrderRepository: care.visify.feature.order.api.repository.market.PendingOrderRepository,
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
            searchRepository.clear()
            pendingOrderRepository.clear()
        }
    }
}