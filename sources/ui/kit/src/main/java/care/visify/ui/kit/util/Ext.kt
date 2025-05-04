@file:Suppress("UNCHECKED_CAST")

package care.visify.ui.kit.util

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.theme.VisifyTheme
import kotlinx.coroutines.launch

const val ZOOM_VELOCITY = 0.5F


val ACCELERATE_DECELERATE by lazy { AccelerateDecelerateInterpolator() }
val ACCELERATE by lazy { AccelerateInterpolator() }

fun Int.dpInt(ctx: Context): Int = this.toFloat().dp(ctx).toInt()
fun Int.dp(ctx: Context): Float = this.toFloat().dp(ctx)
fun Int.spInt(ctx: Context): Int = this.toFloat().sp(ctx).toInt()

fun Float.dp(ctx: Context): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        ctx.resources.displayMetrics
    )
}

fun Float.sp(ctx: Context): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        ctx.resources.displayMetrics
    )
}

fun View.animateWithEasyInOutInterpolator(duration: Long) = animate()
    .setStartDelay(0L)
    .setDuration(duration)
    .setInterpolator(ACCELERATE_DECELERATE)

fun View.animateWithAccelerateInterpolator(duration: Long) = animate()
    .setStartDelay(0L)
    .setDuration(duration)
    .setInterpolator(ACCELERATE)


fun View.animateFadeOutWithEasyInOutInterpolator(duration: Long) =
    animateWithEasyInOutInterpolator(duration)
        .alpha(0f)

fun View.animateFadeInWithEasyInOutInterpolator(duration: Long) =
    animateWithEasyInOutInterpolator(duration)
        .alpha(1f)


fun <T> Any.cast(): T? = this as? T


fun Float.formatRating() = "%.1f".format(this)
    .replace('.', ',')

@Deprecated("Use Visify Containers instead")
@Composable
fun Modifier.cellTop(color: Color = VisifyTheme.colors.frame.white): Modifier = this
    .padding(horizontal = 16.dp)
    .background(
        color,
        RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp)
    )

@Deprecated("Use Visify Containers instead")
@Composable
fun Modifier.cellCenter(color: Color = VisifyTheme.colors.frame.white): Modifier = this
    .padding(horizontal = 16.dp)
    .background(color)

@Deprecated("Use Visify Containers instead")
@Composable
fun Modifier.cellBottom(color: Color = VisifyTheme.colors.frame.white): Modifier = this
    .padding(horizontal = 16.dp)
    .background(
        color,
        RoundedCornerShape(bottomStart = 6.dp, bottomEnd = 6.dp)
    )

@Deprecated("Use Visify Containers instead")
@Composable
fun Modifier.cell(
    color: Color = VisifyTheme.colors.frame.white, padding: Dp = 16.dp,
): Modifier = this
    .padding(horizontal = padding)
    .background(
        color,
        RoundedCornerShape(6.dp)
    )

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Modifier.zoomable(): Modifier {

    val coroutineScope = rememberCoroutineScope()

    val animatedScale = remember { Animatable(1f) }

    var offset by remember { mutableStateOf(Offset.Zero) }

    val state = rememberTransformableState { zoomChange, offsetChange, _ ->

        // to decrease zoom velocity
        val actualZoom = zoomChange.minus(1).let { trunc ->
            zoomChange - (trunc * ZOOM_VELOCITY)
        }

        when (zoomChange) {
            1f -> Unit
            else -> {
                coroutineScope.launch {
                    animatedScale.snapTo(
                        (animatedScale.value * actualZoom).coerceIn(1f, 5f)
                    )
                    offset += offsetChange * ZOOM_VELOCITY
                }
            }
        }
    }

    return this
        .graphicsLayer {
            scaleX = animatedScale.value
            scaleY = animatedScale.value
            translationX = offset.x
            translationY = offset.y
        }
        .transformable(state = state)
        .combinedClickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = { },
            onDoubleClick = {
                coroutineScope.launch {
                    animatedScale.animateTo(1f, tween(200))
                    offset = Offset.Zero
                }
            }
        )
        .clipToBounds()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Modifier.pagerTapSnap(
    pagerState: PagerState,
    otherClick: () -> Unit = { },
): Modifier {
    val scope = rememberCoroutineScope()
    return this
        .pointerInput(Unit) {
            detectTapGestures {

                val isRightTap = it.x > size.width * 3 / 4
                val isLeftTap = it.x < size.width / 4
                when {
                    isRightTap && pagerState.currentPage < (pagerState.pageCount - 1) -> scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }

                    isLeftTap && pagerState.currentPage > 0 -> scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }

                    else -> otherClick()
                }
            }
        }
}