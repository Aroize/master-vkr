package care.visify.business.core.navigator

import care.visify.core.ktx.CoroutineDispatchers
import care.visify.core.navigator.api.VisifyNavigatorBlockingTask
import care.visify.core.navigator.api.VisifyNavigatorTaskFactory
import care.visify.core.navigator.impl.bottomnav.BottomNavBlockingTask
import care.visify.ui.kit.overlay.container.OverlayNavigatorBlockingTask
import javax.inject.Inject

class BusinessNavigatorTaskFactory @Inject constructor(
    private val dispatchers: CoroutineDispatchers,
    private val bottomNavBlockingTask: BottomNavBlockingTask
) : VisifyNavigatorTaskFactory {
    override fun collect(): List<VisifyNavigatorBlockingTask> {
        return listOf(
            OverlayNavigatorBlockingTask(dispatchers),
            bottomNavBlockingTask
        )
    }
}