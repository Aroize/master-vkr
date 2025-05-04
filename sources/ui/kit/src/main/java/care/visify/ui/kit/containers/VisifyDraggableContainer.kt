package care.visify.ui.kit.containers

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.theme.VisifyTheme
import kotlinx.coroutines.launch


@Composable
fun VisifyDraggableContainer(
    modifier: Modifier,
    offsetY: Animatable<Float, AnimationVector1D>,
    onDismiss: () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    val scope = rememberCoroutineScope()
    Box(modifier = modifier) {
        content()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .draggable(
                    orientation = Orientation.Vertical,
                    state = rememberDraggableState { delta ->
                        scope.launch {
                            offsetY.snapTo(
                                maxOf(0f, offsetY.value + delta * 0.5f)
                            )
                        }
                    },
                    onDragStopped = {
                        if (offsetY.value > 240f) {
                            onDismiss()
                        } else {
                            offsetY.animateTo(
                                0f,
                                animationSpec = tween(300)
                            )
                        }
                    }
                )
        ) {
            Box(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .width(32.dp)
                    .height(4.dp)
                    .background(
                        VisifyTheme.colors.frame.disabled,
                        RoundedCornerShape(16.dp)
                    )
                    .align(Alignment.TopCenter)
            )
        }
    }
}
