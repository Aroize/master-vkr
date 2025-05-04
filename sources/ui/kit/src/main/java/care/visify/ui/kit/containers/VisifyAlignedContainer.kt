package care.visify.ui.kit.containers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun VisifyAlignedContainer(
    labels: @Composable ColumnScope.() -> Unit,
    values: @Composable ColumnScope.() -> Unit,
    gap: Dp,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(gap)
    ) {
        Column { labels() }

        Column(modifier = Modifier.weight(1f)) { values() }
    }
}