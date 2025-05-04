package care.visify.ui.kit.detailed

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.containers.cellitem.SelectedState
import care.visify.ui.kit.containers.cellitem.VisifyTextCellItem
import care.visify.ui.kit.modal.VisifyModalBottomSheet
import care.visify.ui.kit.modal.VisifySheetState
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.subheaderPrimary
import care.visify.ui.kit.util.cellShape
import care.visify.ui.kit.util.clickableNoInteraction
import kotlinx.collections.immutable.PersistentList

@Composable
fun CategoriesNavigationBottomSheet(
    state: VisifySheetState<PersistentList<ByCategoryNavigation>>,
    navigateToIndex: (Int) -> Unit,
) {
    VisifyModalBottomSheet(visifySheetState = state) {

        Text(
            text = "Категории",
            style = VisifyTheme.visifyTypography.subheaderPrimary,
            modifier = Modifier.padding(start = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        val items = state.unsafeData

        items.forEachIndexed { index, mastersByCategoryNavigation ->
            val text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = VisifyTheme.colors.label.primary)) {
                    append(mastersByCategoryNavigation.categoryName)
                }
                append("   ")
                withStyle(style = SpanStyle(color = VisifyTheme.colors.label.tertiary)) {
                    append("${mastersByCategoryNavigation.itemCount}")
                }
            }

            VisifyTextCellItem(
                text = text,
                shape = cellShape(items, index, 6.dp),
                hasDivider = index != items.lastIndex,
                selectedState = SelectedState.Gone,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clickableNoInteraction {
                        state.hide()
                        navigateToIndex(index)
                    }
            )
        }
    }
}