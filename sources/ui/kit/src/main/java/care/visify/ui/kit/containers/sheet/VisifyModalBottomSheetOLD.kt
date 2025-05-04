package care.visify.ui.kit.containers.sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.modal.handle.VisifyDragHandle
import care.visify.ui.kit.theme.VisifyTheme
import kotlinx.coroutines.launch

@Deprecated("Use VisifyModalBottomSheet from .modal package")
private const val DEFAULT_SHEET_FRACTION = 0.95f

@Deprecated("Use VisifyModalBottomSheet from .modal package")
data class VisifySheetStateOLD<T>(
    val fraction: Float,
    val isVisible: MutableState<Boolean>,
    val data: MutableState<T>
) {

    fun show() {
        isVisible.value = true
    }

    fun show(content: T) {
        data.value = content
        isVisible.value = true
    }

    fun hide() {
        isVisible.value = false
    }
}

@Deprecated("Use VisifyModalBottomSheet from .modal package")
@Composable
fun<T> rememberVisifySheetStateOLD(
    initial: T
) = rememberVisifySheetStateOLD(initial = initial, fraction = DEFAULT_SHEET_FRACTION)

@Deprecated("Use VisifyModalBottomSheet from .modal package")
@Composable
fun<T> rememberVisifySheetStateOLD(
    initial: T,
    fraction: Float
) = remember {
    VisifySheetStateOLD(
        fraction,
        mutableStateOf(false),
        mutableStateOf(initial)
    )
}

@Deprecated("Use VisifyModalBottomSheet from .modal package")
interface VisifySheetScopeOLD<T>: ColumnScope {
    val state: VisifySheetStateOLD<T>
}

@Deprecated("Use VisifyModalBottomSheet from .modal package")
class VisifySheetScopeOLDInstance<T>(
    override val state: VisifySheetStateOLD<T>,
    columnScope: ColumnScope
): VisifySheetScopeOLD<T>, ColumnScope by columnScope


@Deprecated("Use VisifyModalBottomSheet from .modal package")
@Composable
fun EmptyVisifyFooterOLD(color: Color = VisifyTheme.colors.frame.grey) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp)
            .background(color)
    )
}

@Deprecated("Use VisifyModalBottomSheet from .modal package")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun<T> VisifyModalBottomSheetOLD(
    visifySheetStateOLD: VisifySheetStateOLD<T>,
    footer: @Composable () -> Unit = { EmptyVisifyFooterOLD() },
    dragHandle: (@Composable () -> Unit) = { VisifyDragHandle() },
    onDismiss: () -> Unit = {  },
    content: @Composable VisifySheetScopeOLD<T>.() -> Unit,
) {

    val coroutineScope = rememberCoroutineScope()

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val screenHeight = with(LocalDensity.current) {
        val maxContentHeight = LocalConfiguration.current.screenHeightDp.dp.toPx() * visifySheetStateOLD.fraction
        maxContentHeight.toDp()
    }

    if (visifySheetStateOLD.isVisible.value || sheetState.isVisible) {
        ModalBottomSheet(
            onDismissRequest = {
                visifySheetStateOLD.hide()
                onDismiss()
            },
            sheetState = sheetState,
            shape = RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp),
            containerColor = VisifyTheme.colors.frame.grey,
            dragHandle = dragHandle,
            windowInsets = WindowInsets(0.dp),
            content = {
                Column(
                    modifier = Modifier.heightIn(max = screenHeight)
                ) {
                    VisifySheetScopeOLDInstance(
                        visifySheetStateOLD, this
                    ).content()
                    footer()
                }
            }
        )
        if (visifySheetStateOLD.isVisible.value.not()) {
            coroutineScope.launch { sheetState.hide() }
        }
    }
}