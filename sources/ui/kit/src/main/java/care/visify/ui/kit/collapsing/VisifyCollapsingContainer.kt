package care.visify.ui.kit.collapsing

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Velocity
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min

@Composable
fun VisifyCollapsingContainer(
    state: VisifyCollapsingContainerState,
    nestedScrollConnection: NestedScrollConnection,
    modifier: Modifier = Modifier,
    sticky: @Composable () -> Unit,
    content: VisifyCollapsingContainerScope.() -> Unit
) {

    val scope = VisifyCollapsingContainerScopeImpl(state)
            .apply(content)

    val coroutineScope = rememberCoroutineScope()

    Layout(
        contents = listOf(sticky, { scope.Content() }),
        modifier = modifier
            .clipToBounds()
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { _, amount ->
                        if (nestedScrollConnection.onPreScroll(
                                amount,
                                NestedScrollSource.Drag
                            ) == Offset.Zero) {
                            state.consumeVerticalScroll(amount.y)
                        }
                    },
                    onDragEnd = {
                        coroutineScope.launch {
                            nestedScrollConnection.onPostFling(Velocity.Zero, Velocity.Zero)
                        }
                    }
                )
            }
    ) { measurables, constraints ->

        val stickyMeasurables = measurables.first()

        val stickyPlaceables = stickyMeasurables.map { measurable -> measurable.measure(constraints) }

        val stickyHeight = stickyPlaceables.sumOf { it.height }

        val scrollableMeasurables = measurables.last()
        val scrollablePlaceables = scrollableMeasurables.map { measurable ->
            measurable.measure(constraints)
        }

        state.updateConstraints(scrollablePlaceables.map { it.height.toFloat() })

        val allContentsSize = stickyHeight + scrollablePlaceables.sumOf { it.height }

        val width = constraints.maxWidth
        val height = max(
            stickyHeight,
            min(
                allContentsSize + state.offset.toInt(),
                allContentsSize
            )
        )

        layout(width, height) {
            var yPosition = 0
            stickyPlaceables.forEach { placeable ->
                placeable.placeRelative(0, yPosition, zIndex = 100f)
                yPosition += placeable.height
            }

            var offsetBase = yPosition + min(state.offset.toInt(), 0)

            for (i in scrollablePlaceables.indices) {
                val currentPlacerHeight = scrollablePlaceables[i].height
                scrollablePlaceables[i].placeRelative(
                    x = 0,
                    y = offsetBase
                )
                offsetBase += currentPlacerHeight
            }
        }
    }
}