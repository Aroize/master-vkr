package care.visify.ui.kit.collapsing

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.Velocity
import care.visify.ui.kit.core.VisifyScaffold
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min


@Composable
fun VisifySnappingScaffold(
    overlayContainerHeight: Dp,
    snappingState: VisifySnappingScaffoldState,
    modifier: Modifier = Modifier,
    snackbarHost: SnackbarHostState? = null,
    topBar: @Composable () -> Unit,
    bottomBar: @Composable () -> Unit,
    underlay: @Composable () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val density = LocalDensity.current
    val maxOverlayContainerHeightPx = with(density) { overlayContainerHeight.toPx() }
    val actualTopOffset = remember(overlayContainerHeight) {
        Animatable(with(density) { overlayContainerHeight.toPx() })
    }

    val scope = rememberCoroutineScope()
    snappingState.nestedScrollConnection = remember {
        object : SnappingNestedScrollConnection {

            override suspend fun onFullSnapRequested() {
                if (actualTopOffset.value != 0f) {
                    actualTopOffset.snapTo(0f)
                }
                snappingState.topStateScrollConsumer(Float.NEGATIVE_INFINITY)
            }

            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                return if (available.y < 0) {
                    if (actualTopOffset.value == 0f) {
                        if (snappingState.topStateScrollConsumer(available.y) != 0f) {
                            return available
                        }
                        return Offset.Zero
                    }
                    if (snappingState.canConsumeScroll.value) {
                        return Offset.Zero
                    }
                    val diff = actualTopOffset.value + available.y
                    scope.launch { actualTopOffset.snapTo(max(0f, diff)) }
                    available
                } else {
                    if (actualTopOffset.value == 0f) {
                        if (snappingState.canConsumeScroll.value) {
                            return Offset.Zero
                        }
                        if (snappingState.topStateScrollConsumer(available.y) != 0f) {
                            return available
                        }
                    }
                    if (actualTopOffset.value == maxOverlayContainerHeightPx) {
                        return Offset.Zero
                    }
                    if (snappingState.canConsumeScroll.value) {
                        return Offset.Zero
                    }
                    val diff = actualTopOffset.value + available.y
                    scope.launch { actualTopOffset.snapTo(min(maxOverlayContainerHeightPx, diff)) }
                    available
                }
            }

            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset = available

            override suspend fun onPreFling(available: Velocity): Velocity = Velocity.Zero

            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
                if (actualTopOffset.value > maxOverlayContainerHeightPx / 2) {
                    actualTopOffset.animateTo(maxOverlayContainerHeightPx)
                } else {
                    actualTopOffset.animateTo(0f)
                }
                return available
            }
        }
    }

    VisifyScaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .padding(top = with(density) { actualTopOffset.value.toDp() })
            ) {
                topBar()
            }
        },
        bottomBar = bottomBar,
        content = { padding ->
            Box {
                Box(
                    modifier = Modifier
                        .height(overlayContainerHeight)
                        .align(Alignment.TopCenter),
                    content = { underlay() }
                )

                content(padding)
            }
        },
        modifier = modifier,
        snackbarHost = snackbarHost
    )
}