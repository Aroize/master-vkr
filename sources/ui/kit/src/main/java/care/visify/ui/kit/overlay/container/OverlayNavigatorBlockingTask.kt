package care.visify.ui.kit.overlay.container

import care.visify.core.ktx.CoroutineDispatchers
import care.visify.core.navigator.api.VisifyNavigatorBlockingTask
import care.visify.core.navigator.api.VisifyScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class OverlayNavigatorBlockingTask(
    private val dispatchers: CoroutineDispatchers
) : VisifyNavigatorBlockingTask {
    override suspend fun block(
        prev: VisifyScreen?,
        next: VisifyScreen?
    ) = withContext(dispatchers.io) {
        val overlays = OverlayContainerRegistry.collect()
        val delay = overlays
            .filter { it.isVisible }
            .maxByOrNull { it.animationDurationMillis }
            ?.animationDurationMillis
        overlays.forEach { state ->
            state.isDisposed = state.isVisible
            state.hide()
        }
        if (delay != null && delay > 0) {
            delay(delay.toLong())
        }
    }
}