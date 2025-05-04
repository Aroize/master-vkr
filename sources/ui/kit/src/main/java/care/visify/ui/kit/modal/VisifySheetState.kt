package care.visify.ui.kit.modal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Composable
fun <T> rememberVisifySheetState(initial: T? = null) = remember { VisifySheetState(initial) }

class VisifySheetState<T> internal constructor(initial: T?) {

    private val isVisibleState: MutableState<Boolean> = mutableStateOf(false)
    private val mutableData: MutableState<T?> = mutableStateOf(initial)

    var isVisible: Boolean by isVisibleState
        private set
    var unsafeData: T
        get() = requireNotNull(mutableData.value)
        set(value) {
            mutableData.value = value
        }
    var data by mutableData

    fun show() {
        isVisible = true
    }

    fun show(content: T) {
        unsafeData = content
        isVisible = true
    }

    fun hide() {
        isVisible = false
    }

    internal companion object {
        const val MAX_FRACTION = 0.9F
    }
}