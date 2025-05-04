package care.visify.ui.kit.overlay.container

import androidx.compose.runtime.MutableState
import care.visify.ui.kit.overlay.OverlayState

interface OverlayContainerState {
    var isDisposed: Boolean
    val isVisible: Boolean
    var needToRecovery: Boolean
    val animationDurationMillis: Int
    val state: MutableState<OverlayState>

    fun show()
    fun hide(recovery: Boolean = true)
}