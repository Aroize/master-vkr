package care.visify.ui.kit.collapsing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Composable
fun rememberCollapsingContainerState() = remember { VisifyCollapsingContainerState() }

class VisifyCollapsingContainerState {

    private var maximumConstraints by mutableStateOf(0f)

    internal var fromIndexToStick by mutableStateOf(0)

    internal var offset: Float by mutableStateOf(0f)
        private set

    var isCollapsed by mutableStateOf(false)
        private set

    fun consumeVerticalScroll(scroll: Float): Float {
        if (offset + scroll > 0f) {
            isCollapsed = false
            offset = 0f
            return 0f
        }
        if (offset + scroll < -maximumConstraints) {
            offset = -maximumConstraints
            isCollapsed = true
            return 0f
        }
        isCollapsed = false
        offset += scroll
        return scroll
    }

    fun updateConstraints(constraints: List<Float>) {
        if (fromIndexToStick >= constraints.size) {
            maximumConstraints = constraints.sum()
            return
        }
        maximumConstraints = constraints.subList(fromIndex = 0, toIndex = fromIndexToStick).sum()
    }
}