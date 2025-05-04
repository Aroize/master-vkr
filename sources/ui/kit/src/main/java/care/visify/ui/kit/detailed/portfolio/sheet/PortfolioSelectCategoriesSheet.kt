package care.visify.ui.kit.detailed.portfolio.sheet

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import care.visify.ui.icons.IconsR
import care.visify.ui.kit.categories.CategoryTextCellItem
import care.visify.ui.kit.components.footer.SelectionFooter
import care.visify.ui.kit.components.footer.SelectorState
import care.visify.ui.kit.containers.cellitem.toSelectedState
import care.visify.ui.kit.modal.VisifyModalBottomSheet
import care.visify.ui.kit.modal.VisifySheetState
import care.visify.ui.kit.modal.headers.VisifyModalHeader
import care.visify.ui.kit.util.cellShape
import care.visify.ui.models.category.CategoryUiModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.minus
import kotlinx.collections.immutable.plus

@Composable
fun PortfolioSelectCategoriesSheet(
    state: VisifySheetState<SelectorState<CategoryUiModel>>,
    onIdsSelected: (PersistentList<Int>) -> Unit,
) {
    if (state.data == null) return
    val categories = state.unsafeData.entities
    val selectedIdsState = remember { mutableStateOf(state.unsafeData.selectedIds) }
    var selectedIds by selectedIdsState

    VisifyModalBottomSheet(visifySheetState = state,
        header = {
            VisifyModalHeader("Категория", "Можно выбрать несколько")
        },
        footer = {
            SelectionFooter(
                selectedItems = selectedIdsState,
                onClose = {
                    onIdsSelected(selectedIds)
                    state.hide()
                }
            )
        },
        content = {
            Spacer(modifier = Modifier.height(16.dp))
            categories.forEachIndexed { index, categoryUiModel ->
                CategoryTextCellItem(
                    category = categoryUiModel,
                    hasDivider = index != categories.lastIndex,
                    shape = cellShape(
                        categories,
                        index,
                        size = 6.dp
                    ),
                    selectedState = (categoryUiModel.id in selectedIds)
                        .toSelectedState(
                            selectedIcon = IconsR.ic_radio_on_24,
                            unselectedIcon = IconsR.ic_radio_off_24
                        ),
                ) {
                    if (categoryUiModel.id in selectedIds) {
                        selectedIds -= categoryUiModel.id
                    } else {
                        selectedIds += categoryUiModel.id
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    )
}