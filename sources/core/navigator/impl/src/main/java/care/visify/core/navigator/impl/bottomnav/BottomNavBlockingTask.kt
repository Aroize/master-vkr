package care.visify.core.navigator.impl.bottomnav

import care.visify.core.navigator.api.VisifyNavigatorBlockingTask
import care.visify.core.navigator.api.VisifyScreen
import javax.inject.Inject

class BottomNavBlockingTask @Inject constructor(
    private val stateHolder: BottomNavStateHolder
) : VisifyNavigatorBlockingTask {
    override suspend fun block(
        prev: VisifyScreen?,
        next: VisifyScreen?
    ) {
        val bottomNav = next?.bottomNav ?: return
        stateHolder.reduce(bottomNav)
    }
}