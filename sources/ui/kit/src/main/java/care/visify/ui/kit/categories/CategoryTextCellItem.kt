package care.visify.ui.kit.categories

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.containers.cellitem.SelectedState
import care.visify.ui.kit.containers.cellitem.VisifyTextCellItem
import care.visify.ui.kit.util.clickableNoInteraction
import care.visify.ui.models.category.CategoryUiModel

@Composable
fun CategoryTextCellItem(
    category: CategoryUiModel,
    hasDivider: Boolean,
    shape: Shape,
    selectedState: SelectedState = SelectedState.Gone,
    onClick: (Int) -> Unit,
) {
    VisifyTextCellItem(
        text = category.text,
        shape = shape,
        hasDivider = hasDivider,
        selectedState = selectedState,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickableNoInteraction {
                onClick(category.id)
            }
    )
}