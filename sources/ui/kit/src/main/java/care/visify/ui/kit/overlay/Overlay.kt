package care.visify.ui.kit.overlay

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PixelFormat
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.semantics.popup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.lifecycle.setViewTreeViewModelStoreOwner
import androidx.savedstate.findViewTreeSavedStateRegistryOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import care.visify.ui.kit.overlay.container.OverlayContainerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AnimatedOverlayContainer(
    onDismissRequest: () -> Unit,
    containerState: OverlayContainerState,
    content: @Composable () -> Unit
) {
    val density = LocalDensity.current

    var state by containerState.state

    if (containerState.isVisible) {
        OverlayContainer(onDismissRequest = onDismissRequest) {
            BoxWithConstraints(modifier = Modifier.fillMaxHeight()) {

                val coroutineScope = rememberCoroutineScope()
                val offsetAnimatable = remember { Animatable(constraints.maxHeight.toFloat()) }

                when (state) {
                    OverlayState.NEED_TO_SHOW -> coroutineScope
                        .launch {
                            state = OverlayState.ANIMATING
                            offsetAnimatable.animateTo(
                                0f,
                                animationSpec = tween(
                                    durationMillis = containerState.animationDurationMillis
                                )
                            )
                            delay(containerState.animationDurationMillis.toLong())
                        }.invokeOnCompletion { throwable ->
                            throwable ?: kotlin.run { state = OverlayState.SHOWN }
                        }

                    OverlayState.NEED_TO_HIDE-> coroutineScope
                        .launch {
                            state = OverlayState.ANIMATING
                            offsetAnimatable.animateTo(
                                constraints.maxHeight.toFloat(),
                                animationSpec = tween(
                                    durationMillis = containerState.animationDurationMillis
                                )
                            )
                            delay(containerState.animationDurationMillis.toLong())
                        }.invokeOnCompletion { throwable ->
                            throwable ?: kotlin.run { state = OverlayState.HIDDEN }
                        }
                    OverlayState.HIDDEN,
                    OverlayState.ANIMATING,
                    OverlayState.SHOWN -> Unit
                }

                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .offset(y = with(density) { offsetAnimatable.value.toDp() })
                ) {
                    content()
                }
            }
        }
    }
}

@Composable
fun OverlayContainer(
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit,
) {
    val view = LocalView.current
    val parentComposition = rememberCompositionContext()
    val currentContent by rememberUpdatedState(content)

    val overlay = remember {
        Overlay(
            view,
            onDismissRequest = onDismissRequest
        )
            .apply {
                setCustomContent(parent = parentComposition) {
                    Box(
                        Modifier
                            .semantics { this.popup() }
                            .windowInsetsPadding(WindowInsets(0.dp))
                    ) {
                        currentContent()
                    }
                }
            }
    }

    DisposableEffect(overlay) {

        overlay.show()

        onDispose {
            overlay.disposeComposition()
            overlay.dismiss()
        }
    }
}


@SuppressLint("ViewConstructor")
class Overlay constructor(
    composeView: View,
    private val onDismissRequest: () -> Unit
) : AbstractComposeView(composeView.context) {

    init {
        setViewTreeLifecycleOwner(composeView.findViewTreeLifecycleOwner())
        setViewTreeViewModelStoreOwner(composeView.findViewTreeViewModelStoreOwner())
        setViewTreeSavedStateRegistryOwner(composeView.findViewTreeSavedStateRegistryOwner())
    }

    private val windowManager =
        composeView.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

    private val displayWidth: Int
        get() = context.resources.displayMetrics.widthPixels

    private val params = WindowManager.LayoutParams().apply {
        gravity = Gravity.BOTTOM

        type = WindowManager.LayoutParams.TYPE_APPLICATION_PANEL

        width = displayWidth

        height = WindowManager.LayoutParams.MATCH_PARENT

        format = PixelFormat.TRANSLUCENT
        flags = flags and (
                WindowManager.LayoutParams.FLAG_IGNORE_CHEEK_PRESSES or
                        WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM
                ).inv()
        flags = flags or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
    }

    override var shouldCreateCompositionOnAttachedToWindow: Boolean = false
        private set

    private var content: @Composable () -> Unit by mutableStateOf({})

    @Composable
    override fun Content() {
        content()
    }

    fun setCustomContent(
        parent: CompositionContext? = null,
        content: @Composable () -> Unit
    ) {
        parent?.let { setParentCompositionContext(it) }
        this.content = content
        shouldCreateCompositionOnAttachedToWindow = true
    }

    fun show() {
        windowManager.addView(this, params)
    }

    fun dismiss() {
        setViewTreeLifecycleOwner(null)
        setViewTreeSavedStateRegistryOwner(null)
        windowManager.removeViewImmediate(this)
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        if (event.keyCode == KeyEvent.KEYCODE_BACK) {
            if (keyDispatcherState == null) {
                return super.dispatchKeyEvent(event)
            }
            if (event.action == KeyEvent.ACTION_DOWN && event.repeatCount == 0) {
                val state = keyDispatcherState
                state?.startTracking(event, this)
                return true
            } else if (event.action == KeyEvent.ACTION_UP) {
                val state = keyDispatcherState
                if (state != null && state.isTracking(event) && !event.isCanceled) {
                    onDismissRequest()
                    return true
                }
            }
        }
        return super.dispatchKeyEvent(event)
    }
}