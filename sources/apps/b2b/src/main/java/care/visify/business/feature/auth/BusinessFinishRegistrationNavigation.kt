package care.visify.business.feature.auth

import care.visify.core.navigator.api.VisifyRouter
import care.visify.feature.auth.api.nav.FinishRegistrationNavigation
import care.visify.feature.dashboard.api.screen.DashboardScreen
import javax.inject.Inject

class BusinessFinishRegistrationNavigation @Inject constructor(
    private val router : VisifyRouter
) : FinishRegistrationNavigation {
    override suspend fun navigate() {
        router.newRootChain(DashboardScreen)
    }
}