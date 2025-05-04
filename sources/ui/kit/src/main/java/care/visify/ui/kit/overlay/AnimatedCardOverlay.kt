package care.visify.ui.kit.overlay

import androidx.compose.runtime.Composable
import care.visify.ui.kit.containers.sheet.VisifyBottomSheetCard
import care.visify.ui.kit.overlay.container.OverlayContainerState

@Composable
fun AnimatedCardOverlay(
    onDismissRequest: () -> Unit,
    containerState: OverlayContainerState,
    content: @Composable () -> Unit,
) {
    AnimatedOverlayContainer(
        onDismissRequest = onDismissRequest,
        containerState = containerState
    ) {
        VisifyBottomSheetCard {
            content()
        }
    }
}