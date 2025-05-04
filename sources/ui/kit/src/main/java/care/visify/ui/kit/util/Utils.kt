package care.visify.ui.kit.util

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import care.visify.ui.kit.theme.VisifyTheme
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@Immutable
data class ScrollContext(
    val lastVisibleIndex: Int
)

fun Modifier.clickableNoInteraction(onClick: () -> Unit): Modifier = composed {
    this.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        onClick = onClick
    )
}

fun Modifier.drawTabUnderline(isDraw: Boolean) : Modifier = composed {
    val underlineColor = VisifyTheme.colors.label.primary
    this.then(
        Modifier.drawBehind {
            if (isDraw) {
                val strokeWidth = 2.dp.toPx()
                val y = size.height + 3.dp.toPx()
                drawLine(
                    color = underlineColor,
                    start = Offset(0f, y),
                    end = Offset(size.width, y),
                    strokeWidth = strokeWidth
                )
            }
        }
    )
}

fun Modifier.asBgColor(): Modifier = composed {
    this.background(VisifyTheme.colors.frame.grey)
}

fun Modifier.visifyStatusBarPadding() : Modifier = composed {
    val statusBarOffset = with(LocalDensity.current) {
        WindowInsets.statusBars.getTop(this).toDp()
    }
    this.padding(top = statusBarOffset)
}

fun Modifier.gesturesDisabled(disabled: Boolean = true) =
    if (disabled) {
        pointerInput(Unit) {
            awaitPointerEventScope {
                // we should wait for all new pointer events
                while (true) {
                    awaitPointerEvent(pass = PointerEventPass.Initial)
                        .changes
                        .forEach(PointerInputChange::consume)
                }
            }
        }
    } else {
        this
    }

val LazyListState.lastItemVisible: Int
    get() = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -1


@Composable
fun LazyListState.rememberScrollContext(): ScrollContext {
    val scrollContext by remember {
        derivedStateOf {
            ScrollContext(
                lastVisibleIndex = lastItemVisible
            )
        }
    }
    return scrollContext
}

fun LazyListState.onNewContentRequested(
    preload: Int = 5,
    scrollContext: ScrollContext,
    onNewContent: () -> Unit
) {
    if (scrollContext.lastVisibleIndex < 0) return
    if (scrollContext.lastVisibleIndex + preload > layoutInfo.totalItemsCount) {
        onNewContent()
    }
}

@Composable
fun PagingRequester(
    state: LazyListState,
    callback: () -> Unit
) {
    LaunchedEffect(state) {
        val totalItems = snapshotFlow { state.layoutInfo.totalItemsCount }
        val lastVisibleItem = snapshotFlow { state.lastItemVisible }
        combine(totalItems, lastVisibleItem) { total, last -> total to last }
            .map { (total, last) -> last + 5 >= total }
            .distinctUntilChanged()
            .filter { it }
            .collect { _ ->
                callback()
            }
    }
}

@Deprecated("Use PagingRequester instead")
@Composable
fun OnNewContentRequester(
    state: LazyListState,
    preload: Int = 5,
    onNewContent: () -> Unit
) {
    val scrollContext = state.rememberScrollContext()
    state.onNewContentRequested(preload, scrollContext, onNewContent)
}

@Deprecated("Use PagingRequester instead")
@Composable
fun<T> Collection<T>.OnNewContentRequester(
    state: LazyListState,
    total: Int,
    preload: Int = 5,
    onNewContent: () -> Unit
) {
    if (size < total) {
        OnNewContentRequester(state, preload, onNewContent)
    }
}

fun<T> cellShape(
    data: List<T>,
    index: Int,
    size: Dp = 6.dp
): Shape {
    return cellShape(data.size, index, size)
}

fun cellShape(
    all: Int,
    index: Int,
    size: Dp = 6.dp
): Shape {
    return when {
        all == 1 -> RoundedCornerShape(size)
        index == 0 -> RoundedCornerShape(topStart = size, topEnd = size)
        index == all - 1 -> RoundedCornerShape(bottomStart = size, bottomEnd = size)
        else -> RectangleShape
    }
}

val TextUnit.Companion.VectorConverter: TwoWayConverter<TextUnit, AnimationVector1D>
    get() = TwoWayConverter(
        convertToVector = { AnimationVector1D(it.value) },
        convertFromVector = { it.value.toInt().sp },
    )

@Composable
fun animateSpAsState(
    targetValue: TextUnit,
    animationSpec: AnimationSpec<TextUnit> = spring(),
    label: String = "SpAnimation",
    finishedListener: ((TextUnit) -> Unit)? = null
): State<TextUnit> {
    return animateValueAsState(
        targetValue,
        TextUnit.VectorConverter,
        animationSpec,
        label = label,
        finishedListener = finishedListener
    )
}

@Composable
fun OverrideBackPressed(
    flag: Boolean,
    custom: () -> Unit
) {
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    var backPressHandled by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    BackHandler(enabled = !backPressHandled) {
        if (flag) {
            custom()
        } else {
            backPressHandled = true
            coroutineScope.launch {
                awaitFrame()
                onBackPressedDispatcher?.onBackPressed()
                backPressHandled = false
            }
        }
    }
}

fun Int.getTimeDefaultStr(): String =  "${if (this <= 9) "0" else ""}$this"

fun Int.pixelsToDp(context: Context): Float {
    val densityDpi = context.resources.displayMetrics.densityDpi
    return this / (densityDpi / 160f)
}