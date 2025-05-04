package care.visify.ui.kit.modal.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.components.input.VisifyInputField
import care.visify.ui.kit.containers.VisifyColumn
import care.visify.ui.kit.modal.VisifyModalBottomSheet
import care.visify.ui.kit.modal.VisifySheetState
import care.visify.ui.kit.modal.footers.GeneralModalFooter
import care.visify.ui.kit.modal.footers.WideButtonFooter
import care.visify.ui.kit.modal.headers.VisifyModalHeader

@Composable
fun QueryInputBottomSheet(
    state: VisifySheetState<QueryInputState>,
    title: String,
    textHint: String,
    saveButtonText: String,
    subtitle : String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    sheetType : QueryInputSheetType = QueryInputSheetType.SMALL,
    onQueryChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    onDismissAction: () -> Unit = {},
) {
    val actualState = state.data ?: return

    val sheetModifier = when (sheetType) {
        QueryInputSheetType.BIG -> Modifier.fillMaxHeight()
        QueryInputSheetType.SMALL -> Modifier
    }

    VisifyModalBottomSheet(
        visifySheetState = state,
        contentModifier = sheetModifier,
        header = {
            VisifyModalHeader(titleText = title, subtitleText = subtitle)
        },
        footer = {
            when (sheetType) {
                QueryInputSheetType.BIG -> {
                    GeneralModalFooter(
                        rightText = saveButtonText,
                        isRightEnable = actualState.query.isNotEmpty(),
                        onRightClick = onSaveClick,
                        leftText = "Отменить",
                        onLeftClick = { state.hide() }
                    )
                }
                QueryInputSheetType.SMALL -> {
                    WideButtonFooter(
                        label = saveButtonText,
                        enabled = actualState.query.isNotEmpty(),
                        onClick = onSaveClick
                    )
                }
            }
        },
        onDismiss = onDismissAction,
    ) {
        VisifyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {

            val singleLine = when (sheetType) {
                QueryInputSheetType.BIG -> false
                QueryInputSheetType.SMALL -> true
            }

            VisifyInputField(
                value = actualState.query,
                hint = textHint,
                onValueChange = onQueryChange,
                keyboardOptions = keyboardOptions,
                singleLine = singleLine
            )
        }
    }
}

enum class QueryInputSheetType {
    BIG, SMALL
}