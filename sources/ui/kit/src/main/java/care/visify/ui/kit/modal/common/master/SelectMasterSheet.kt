package care.visify.ui.kit.modal.common.master

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.modal.VisifyModalBottomSheet
import care.visify.ui.kit.modal.VisifySheetState
import care.visify.ui.kit.modal.footers.WideButtonFooter
import care.visify.ui.kit.modal.headers.VisifyModalHeader
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.mainTextPrimary
import care.visify.ui.kit.util.cellShape
import care.visify.ui.models.master.MasterUiModel

@Composable
fun SelectMasterSheet(
    state: VisifySheetState<SelectMasterState>,
    title: String,
    buttonText: String,

    onSelect: (MasterUiModel) -> Unit,
    onSaveClick: () -> Unit,
    onDismissAction: () -> Unit = {},
) {

    val actualState = state.data ?: return

    VisifyModalBottomSheet(
        visifySheetState = state,
        header = {
            VisifyModalHeader(titleText = title)
        },
        footer = {
            WideButtonFooter(
                label = buttonText,
                enabled = actualState.isAnySelected,
                onClick = onSaveClick
            )
        },
        onDismiss = onDismissAction,
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        )

        if (actualState.masters.isNotEmpty()) {
            actualState.masters.forEachIndexed { index, master ->
                SelectMasterProfessionItem(
                    modifier = Modifier,
                    master = master,
                    shape = cellShape(actualState.masters, index, 6.dp),
                    hasDivider = index != actualState.masters.lastIndex,
                    isSelected = master.id == actualState.selectedMaster?.id,
                    onMasterClick = onSelect
                )
            }
        } else {
            Text(
                "Нет свободных мастеров",
                style = VisifyTheme.visifyTypography.mainTextPrimary,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        )
    }
}