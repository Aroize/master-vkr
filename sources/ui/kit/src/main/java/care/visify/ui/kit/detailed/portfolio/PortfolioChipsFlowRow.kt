package care.visify.ui.kit.detailed.portfolio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.components.chips.ChipsData
import care.visify.ui.kit.components.chips.ChipsItem
import care.visify.ui.kit.components.chips.chips
import care.visify.ui.kit.theme.VisifyTheme
import kotlinx.collections.immutable.PersistentList

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PortfolioChipsFlowRow(
    modifier: Modifier = Modifier,
    chips: PersistentList<ChipsData>,
    onClick: (Int) -> Unit,
    onRemove: (Int) -> Unit,
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        modifier = modifier
            .background(VisifyTheme.colors.frame.grey)
    ) {
        chips.forEach { chips ->
            ChipsItem(
                text = chips.text,
                state = chips.selected.chips(),
                onClick = { onClick(chips.id) },
                onRemove = { onRemove(chips.id) }
            )
        }
    }
}