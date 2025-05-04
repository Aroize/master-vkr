package care.visify.ui.kit.containers.sheet.sorting

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.containers.cellitem.VisifyTextCellItem
import care.visify.ui.kit.containers.cellitem.toSelectedStateWithGone
import care.visify.ui.kit.modal.VisifyModalBottomSheet
import care.visify.ui.kit.modal.VisifySheetState
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.subheaderPrimary
import care.visify.ui.kit.util.cellShape
import care.visify.ui.kit.util.clickableNoInteraction
import kotlinx.collections.immutable.PersistentList

@Composable
fun SortingsBottomSheet(
    state: VisifySheetState<PersistentList<Sorting>>,
    onSortingSelected: (Sorting.Type) -> Unit
) {
    VisifyModalBottomSheet(visifySheetState = state) {
        val sortings = state.unsafeData
        Text(
            text = "Сортировать",
            style = VisifyTheme.visifyTypography.subheaderPrimary,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        sortings.forEachIndexed { index, sorting ->
            VisifyTextCellItem(
                text = stringResource(id = sorting.name),
                shape = cellShape(sortings, index, 6.dp),
                hasDivider = index != sortings.lastIndex,
                selectedState = sorting.selected.toSelectedStateWithGone(),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clickableNoInteraction {
                        onSortingSelected(sorting.type)
                        state.hide()
                    }
            )
        }
    }
}