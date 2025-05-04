package care.visify.ui.kit.overlay.navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import care.visify.core.navigator.api.compose.VisifyComposableScreen
import care.visify.ui.kit.overlay.container.rememberOverlayContainerState


@Composable
inline fun <reified T> LinkComposableScreen(screen: T) where T : VisifyComposableScreen {
    val composable = globalRegistry[T::class.java] ?: throw IllegalArgumentException("Can't find registry for $screen")
    composable.invoke(screen)
}

@Composable
inline fun <reified T : VisifyComposableScreen> LinkOverlayScreen(navigator: OverlayNavigator<T>) {
    CompositionLocalProvider(LocalOverlayContainer provides navigator.container) {
        LinkComposableScreen(navigator.target)
    }
}

@Composable
fun <S : VisifyComposableScreen> rememberOverlayNavigator(target: S): OverlayNavigator<S> {
    val containerState = rememberOverlayContainerState(key = target)
    return rememberSaveable { OverlayNavigator(mutableStateOf(target), containerState) }
}