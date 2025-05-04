package care.visify.ui.kit.collapsing

import androidx.compose.runtime.Composable

interface VisifyCollapsingContainerScope {
    fun stick(content: @Composable () -> Unit)
    fun scroll(content: @Composable () -> Unit)
}

internal class VisifyCollapsingContainerScopeImpl(
    private val state: VisifyCollapsingContainerState
) : VisifyCollapsingContainerScope {

    private val scrollableContainer: MutableList<@Composable () -> Unit> = mutableListOf()
    private val stickyContainer: MutableList<@Composable () -> Unit> = mutableListOf()

    @Composable
    fun Content() {
        scrollableContainer.forEach { it.invoke() }
        stickyContainer.forEach { it.invoke() }
    }

    override fun stick(content: @Composable () -> Unit) {
        stickyContainer.add(content)
    }

    override fun scroll(content: @Composable () -> Unit) {
        scrollableContainer.add(content)
        state.fromIndexToStick = scrollableContainer.size
    }
}