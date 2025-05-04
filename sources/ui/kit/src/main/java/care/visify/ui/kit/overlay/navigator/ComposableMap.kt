package care.visify.ui.kit.overlay.navigator

import androidx.compose.runtime.Composable
import care.visify.core.navigator.api.compose.VisifyComposableScreen


val globalRegistry = ComposableRegistry(mutableMapOf())

class ComposableRegistry(
    private val delegate: MutableMap<Class<*>, @Composable (VisifyComposableScreen) -> Unit>
) : MutableMap<Class<*>, @Composable (VisifyComposableScreen) -> Unit> by delegate