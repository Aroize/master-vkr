package care.visify.ui.kit.modal.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.detailed.favor.FavorExpandableItem
import care.visify.ui.kit.detailed.favor.FavorStyle
import care.visify.ui.kit.modal.VisifyModalBottomSheet
import care.visify.ui.kit.modal.VisifySheetState
import care.visify.ui.kit.modal.footers.SelectModalFooter
import care.visify.ui.kit.modal.headers.VisifyModalHeader

@Composable
fun SelectCategoryFavorSheet(
    state: VisifySheetState<CategoryFavorState>,
    onFavorClick: (Int) -> Unit,
    onSelectClick: () -> Unit,
) {
    val actualState = state.data ?: return

    VisifyModalBottomSheet(
        visifySheetState = state,
        header = { VisifyModalHeader(titleText = "Услуга") },
        footer = {
            SelectModalFooter(
                isEnable = actualState.selectedFavorId != null,
                onSelectClick = onSelectClick
            )
        },
        contentModifier = Modifier.fillMaxSize()
    ) {
        Column {
            Spacer(
                modifier = Modifier
                    .height(12.dp)
            )
            actualState.favors.forEach {
                FavorExpandableItem(
                    item = it,
                    modifier = Modifier
                        .fillMaxWidth(),
                    isExpanded = true,
                    style = FavorStyle.Compact,
                    selectedId = actualState.selectedFavorId,
                    onSelect = onFavorClick
                )
                Spacer(
                    modifier = Modifier
                        .height(12.dp)
                )
            }
        }
    }
}