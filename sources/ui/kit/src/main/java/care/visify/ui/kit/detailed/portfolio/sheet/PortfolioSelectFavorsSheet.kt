package care.visify.ui.kit.detailed.portfolio.sheet

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import care.visify.ui.icons.IconsR
import care.visify.ui.kit.components.footer.SelectionFooter
import care.visify.ui.kit.components.footer.SelectorState
import care.visify.ui.kit.containers.cellitem.VisifyTextCellItem
import care.visify.ui.kit.containers.cellitem.toSelectedState
import care.visify.ui.kit.modal.VisifyModalBottomSheet
import care.visify.ui.kit.modal.VisifySheetState
import care.visify.ui.kit.modal.headers.VisifyModalHeader
import care.visify.ui.kit.util.cellShape
import care.visify.ui.kit.util.clickableNoInteraction
import care.visify.ui.models.favor.FavorUiModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.minus
import kotlinx.collections.immutable.plus

@Composable
fun PortfolioSelectFavorsSheet(
    state: VisifySheetState<SelectorState<FavorUiModel>>,
    onIdsSelected: (PersistentList<Int>) -> Unit,
) {
    if (state.data == null) return
    val favors = state.unsafeData.entities
    val selectedIdsState = remember { mutableStateOf(state.unsafeData.selectedIds) }
    var selectedIds by selectedIdsState

    VisifyModalBottomSheet(visifySheetState = state, header = {
        VisifyModalHeader(titleText = "Услуга", subtitleText = "Можно выбрать несколько")
    }, footer = {
        SelectionFooter(
            selectedItems = selectedIdsState,
            onClose = {
                onIdsSelected(selectedIds)
                state.hide()
            }
        )
    }, content = {
        Spacer(modifier = Modifier.height(16.dp))
        favors.forEachIndexed { index, favorUiModel ->
            VisifyTextCellItem(
                text = favorUiModel.name,
                shape = cellShape(favors, index, size = 6.dp),
                hasDivider = index != favors.lastIndex,
                selectedState = (favorUiModel.id in selectedIds)
                    .toSelectedState(
                        selectedIcon = IconsR.ic_radio_on_24,
                        unselectedIcon = IconsR.ic_radio_off_24
                    ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clickableNoInteraction {
                        if (favorUiModel.id in selectedIds) {
                            selectedIds -= favorUiModel.id
                        } else {
                            selectedIds += favorUiModel.id
                        }
                    }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    })
}