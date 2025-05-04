package care.visify.client.feature.auth.nav

import care.visify.core.navigator.api.VisifyRouter
import care.visify.feature.auth.api.nav.FinishRegistrationNavigation
import javax.inject.Inject

class ClientFinishRegistrationNavigation @Inject constructor(
    private val router : VisifyRouter
) : FinishRegistrationNavigation {
    override suspend fun navigate() {
        router.backToLastNotAuth()
    }
}