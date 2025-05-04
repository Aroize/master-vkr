package care.visify.ui.kit.detailed.favor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.containers.VisifyColumn
import care.visify.ui.kit.containers.cellitem.VisifyCellItem
import care.visify.ui.kit.containers.cellitem.toSelectedStateWithGone
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.buttonActive
import care.visify.ui.kit.theme.values.buttonTertiary
import care.visify.ui.kit.theme.values.mainCellPrimary
import care.visify.ui.kit.theme.values.microTertiary
import care.visify.ui.kit.theme.values.subheaderPrimary
import care.visify.ui.kit.util.cellShape
import care.visify.ui.kit.util.clickableNoInteraction
import care.visify.ui.models.favor.FavorUiModel
import care.visify.ui.models.favor.FavorsByCategoryUiModel
import kotlinx.collections.immutable.toPersistentList

@Composable
fun FavorExpandableItem(
    item: FavorsByCategoryUiModel,
    modifier: Modifier = Modifier,
    isExpanded: Boolean = false,
    style: FavorStyle = FavorStyle.Wide,
    selectedId: Int? = null,
    onSelect: (Int) -> Unit = {  }
) {

    var expanded by rememberSaveable { mutableStateOf(isExpanded) }

    VisifyColumn(
        modifier = modifier
            .padding(horizontal = VisifyTheme.padding._16dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
        ) {
            Text(
                text = item.category,
                style = VisifyTheme.visifyTypography.subheaderPrimary
            )
            if (style == FavorStyle.Wide) {
                Text(
                    text = item.favors.size.toString(),
                    style = VisifyTheme.visifyTypography.buttonTertiary
                )
            }
        }

        val visibleFavors by derivedStateOf {
            if (expanded)
                item.favors
            else
                item.favors.take(2).toPersistentList()
        }

        val showExpandCell by derivedStateOf { expanded.not() && item.favors.size > 2 }

        visibleFavors.forEachIndexed { index, favor ->
            VisifyCellItem(
                shape = cellShape(visibleFavors, index),
                hasDivider = index != visibleFavors.lastIndex || showExpandCell,
                selectedState = (favor.id == selectedId).toSelectedStateWithGone(),
                modifier = Modifier.heightIn(min = 64.dp),
                innerPadding = PaddingValues(end = 16.dp),
                dividerPadding = PaddingValues(start = 16.dp)
            ) { iconRef ->
                val favorRef = createRef()
                FavorItem(
                    item = favor,
                    style = style,
                    modifier = Modifier
                        .constrainAs(favorRef) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(iconRef.end)
                            bottom.linkTo(parent.bottom)
                        }
                        .clickableNoInteraction { onSelect(favor.id) }
                )
            }
        }
        if (showExpandCell) {
            Text(
                text = "Еще...",
                style = VisifyTheme.visifyTypography.buttonActive,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 20.dp)
                    .clickableNoInteraction { expanded = true }
            )
        }
    }
}

enum class FavorStyle {
    Compact, Wide
}

@Composable
fun FavorItem(
    item: FavorUiModel,
    modifier: Modifier,
    style: FavorStyle = FavorStyle.Wide
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = item.name,
                style = VisifyTheme.visifyTypography.mainCellPrimary
            )
            if (style == FavorStyle.Wide)
                Text(
                    text = item.price,
                    style = VisifyTheme.visifyTypography.mainCellPrimary,
                    modifier = Modifier.padding(start = 12.dp)
                )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            val timeText = if (style == FavorStyle.Compact) {
                buildAnnotatedString {
                    append("${item.time} • ")
                    withStyle(SpanStyle(color = VisifyTheme.colors.label.primary)) {
                        append(item.price)
                    }
                }
            } else {
                buildAnnotatedString { append(item.time) }
            }
            Text(
                text = timeText,
                style = VisifyTheme.visifyTypography.microTertiary
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}