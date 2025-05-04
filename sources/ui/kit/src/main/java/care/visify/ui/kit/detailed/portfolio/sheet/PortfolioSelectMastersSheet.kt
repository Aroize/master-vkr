package care.visify.ui.kit.detailed.portfolio.sheet

import androidx.compose.foundation.layout.Spacer
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
import care.visify.ui.kit.containers.cellitem.toSelectedState
import care.visify.ui.kit.detailed.master.MasterItem
import care.visify.ui.kit.detailed.master.MasterRating
import care.visify.ui.kit.modal.VisifyModalBottomSheet
import care.visify.ui.kit.modal.VisifySheetState
import care.visify.ui.kit.modal.headers.VisifyModalHeader
import care.visify.ui.kit.util.cellShape
import care.visify.ui.kit.util.clickableNoInteraction
import care.visify.ui.models.master.MasterUiModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.minus
import kotlinx.collections.immutable.plus

@Composable
fun PortfolioSelectMastersSheet(
    state: VisifySheetState<SelectorState<MasterUiModel>>,
    onIdsSelected: (PersistentList<Int>) -> Unit,
) {
    if (state.data == null) return
    val masters = state.unsafeData.entities
    val selectedIdsState = remember { mutableStateOf(state.unsafeData.selectedIds) }
    var selectedIds by selectedIdsState

    VisifyModalBottomSheet(visifySheetState = state, header = {
        VisifyModalHeader(titleText = "Мастер", subtitleText = "Можно выбрать несколько")
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
        masters.forEachIndexed { index, masterUiModel ->
            val isSelected = masterUiModel.id in selectedIds
            MasterItem(
                master = masterUiModel,
                shape = cellShape(masters, index, 6.dp),
                hasDivider = index != masters.lastIndex,
                masterRating = MasterRating.ABSENT,
                selectedState = isSelected
                    .toSelectedState(
                        selectedIcon = IconsR.ic_radio_on_24,
                        unselectedIcon = IconsR.ic_radio_off_24
                    ),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clickableNoInteraction {
                        if (masterUiModel.id in selectedIds) {
                            selectedIds -= masterUiModel.id
                        } else {
                            selectedIds += masterUiModel.id
                        }
                    }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    })
}