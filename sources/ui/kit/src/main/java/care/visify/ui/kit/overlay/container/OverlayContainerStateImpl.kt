package care.visify.ui.kit.overlay.container

import android.os.Parcel
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import care.visify.ui.kit.overlay.OverlayState
import kotlinx.coroutines.delay


class OverlayContainerStateImpl(
    override var isDisposed: Boolean = false,
    override var needToRecovery: Boolean = true
) : Parcelable, OverlayContainerState {

    override val animationDurationMillis: Int = 200

    override val state: MutableState<OverlayState> = mutableStateOf(OverlayState.HIDDEN)

    override val isVisible: Boolean
        get() = state.value != OverlayState.HIDDEN

    constructor(parcel: Parcel) : this(parcel.readByte() != 0.toByte())

    override fun show() {
        if (state.value == OverlayState.HIDDEN) {
            state.value = OverlayState.NEED_TO_SHOW
        }
    }

    override fun hide(recovery: Boolean) {
        if (state.value == OverlayState.SHOWN) {
            state.value = OverlayState.NEED_TO_HIDE
            this.needToRecovery = recovery
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (isDisposed) 1 else 0)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<OverlayContainerStateImpl> {
        override fun createFromParcel(parcel: Parcel): OverlayContainerStateImpl {
            return OverlayContainerStateImpl(parcel)
        }

        override fun newArray(size: Int): Array<OverlayContainerStateImpl?> {
            return arrayOfNulls(size)
        }
    }
}

@Composable
fun rememberOverlayContainerState(key: Any): OverlayContainerState {
    val state = rememberSaveable<OverlayContainerState>(
        saver = Saver(
            save = { state ->
                if (state.needToRecovery) {
                    OverlayContainerRegistry.register(state)
                } else {
                    state
                }
            },
            restore = { state ->
                if (state.needToRecovery) {
                    OverlayContainerRegistry.register(state)
                } else {
                    state
                }
            }
        )
    ) { OverlayContainerStateImpl() }

    LaunchedEffect(key) {
        if (state.isDisposed && state.needToRecovery) {
            state.isDisposed = false
            delay(400L)
            state.show()
        }
    }

    DisposableEffect(key) {
        onDispose {
            state.state.value = OverlayState.HIDDEN
            OverlayContainerRegistry.unregister(state)
        }
    }
    return state
}