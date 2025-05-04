package care.visify.ui.kit.overlay.navigator

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.referentialEqualityPolicy
import care.visify.ui.kit.overlay.container.OverlayContainerState
import care.visify.ui.kit.overlay.container.OverlayContainerStateImpl

val LocalOverlayContainer = compositionLocalOf<OverlayContainerState>(
    policy = referentialEqualityPolicy(),
    defaultFactory = { OverlayContainerStateImpl() }
)