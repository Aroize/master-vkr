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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.containers.VisifyColumn
import care.visify.ui.kit.containers.cellitem.SelectedState
import care.visify.ui.kit.containers.cellitem.VisifyCellItem
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.buttonActive
import care.visify.ui.kit.theme.values.buttonTertiary
import care.visify.ui.kit.theme.values.mainCellPrimary
import care.visify.ui.kit.theme.values.microPrimary
import care.visify.ui.kit.theme.values.microSecondary
import care.visify.ui.kit.theme.values.microTertiary
import care.visify.ui.kit.theme.values.subheaderPrimary
import care.visify.ui.kit.util.cellShape
import care.visify.ui.kit.util.clickableNoInteraction
import care.visify.ui.models.favor.ConflictingFavorsUiModel
import care.visify.ui.models.favor.MultipleOrgsFavorsByCategoryUiModel
import kotlinx.collections.immutable.toPersistentList

@Composable
fun MultipleOrgsFavorByCategoryItem(
    item: MultipleOrgsFavorsByCategoryUiModel,
    modifier: Modifier = Modifier,
    isExpanded: Boolean = false,
) {
    var expanded by remember { mutableStateOf(isExpanded) }

    VisifyColumn(
        modifier = modifier
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
            Text(
                text = item.favors.size.toString(),
                style = VisifyTheme.visifyTypography.buttonTertiary
            )
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
                selectedState = SelectedState.Gone,
                modifier = Modifier.heightIn(min = 64.dp),
                innerPadding = PaddingValues(),
                dividerPadding = PaddingValues(horizontal = 16.dp)
            ) { iconRef ->
                val favorRef = createRef()
                val constraints = Modifier
                    .constrainAs(favorRef) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(iconRef.end)
                        bottom.linkTo(parent.bottom)
                    }
                if (favor.isConflicting) {
                    ConflictingFavorsItem(
                        item = favor,
                        modifier = constraints
                    )
                } else {
                    val singleFavor = favor.toFavorUiModel()
                    FavorItem(
                        item = singleFavor,
                        modifier = constraints
                    )
                }

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

@Composable
fun ConflictingFavorsItem(
    item: ConflictingFavorsUiModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier.padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = item.name,
                style = VisifyTheme.visifyTypography.mainCellPrimary,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Start
            )
            Text(
                text = item.priceFrom,
                style = VisifyTheme.visifyTypography.mainCellPrimary
            )
        }
        Spacer(modifier = Modifier.height(2.dp))

        item.favors.forEach { favor ->
            Spacer(modifier = Modifier.height(2.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = favor.price,
                    style = VisifyTheme.visifyTypography.microPrimary
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = favor.time,
                    style = VisifyTheme.visifyTypography.microTertiary
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = favor.orgTitle,
                    style = VisifyTheme.visifyTypography.microSecondary
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}