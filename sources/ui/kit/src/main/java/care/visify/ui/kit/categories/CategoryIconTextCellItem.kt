package care.visify.ui.kit.categories

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.Dimension
import care.visify.ui.kit.components.image.VisifyImageById
import care.visify.ui.kit.containers.cellitem.SelectedState
import care.visify.ui.kit.containers.cellitem.VisifyCellItem
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.mainCellPrimary
import care.visify.ui.kit.util.clickableNoInteraction
import care.visify.ui.models.category.CategoryUiModel

@Composable
fun CategoryIconTextCellItem(
    category: CategoryUiModel,
    hasDivider: Boolean,
    shape: Shape,
    onClick: (Int) -> Unit,
) {
    VisifyCellItem(
        shape = shape,
        hasDivider = hasDivider,
        selectedState = SelectedState.Gone,
        modifier = Modifier
            .fillMaxWidth()
            .clickableNoInteraction {
                onClick(category.id)
            }
    ) { iconRef ->
        val (imageRef, nameRef) = createRefs()
        VisifyImageById(
            model = category.icon,
            modifier = Modifier
                .size(26.dp)
                .constrainAs(imageRef) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )
        Text(
            text = category.text,
            style = VisifyTheme.visifyTypography.mainCellPrimary,
            modifier = Modifier
                .constrainAs(nameRef) {
                    bottom.linkTo(imageRef.bottom)
                    top.linkTo(imageRef.top, margin = 5.dp)
                    start.linkTo(imageRef.end, margin = 12.dp)
                    end.linkTo(iconRef.start, margin = 16.dp)
                    width = Dimension.fillToConstraints
                }
        )
    }
}