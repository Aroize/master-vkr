package care.visify.ui.kit.containers.sheet

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import care.visify.ui.kit.modal.VisifyModalBottomSheet
import care.visify.ui.kit.modal.VisifySheetState
import care.visify.ui.kit.modal.footers.SelectModalFooter
import care.visify.ui.kit.modal.headers.VisifyModalHeader

@Composable
fun <T> VisifySelectBottomSheet(
    visifySheetState: VisifySheetState<T>,
    title: String,
    isSelectEnable: Boolean,
    onSelectClick: () -> Unit,
    onDismiss: () -> Unit = {},
    hasBackward: Boolean = false,
    onBackwardClick: () -> Unit = {},
    content: @Composable ColumnScope.(T) -> Unit,
) {
    VisifyModalBottomSheet(
        visifySheetState = visifySheetState,
        header = {
            VisifyModalHeader(titleText = title)
        },
        footer = {
            if (hasBackward) {
                //todo
            } else {
                SelectModalFooter(isSelectEnable, onSelectClick)
            }
        },
        onDismiss = onDismiss,
    ) {
        content(visifySheetState.unsafeData)
    }
}

