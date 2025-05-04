package care.visify.ui.kit.detailed.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.components.shimmer.WeightedShimmerTape
import care.visify.ui.kit.theme.VisifyTheme

@Composable
fun AboutShimmerContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = VisifyTheme.padding._16dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        WeightedShimmerTape(weight = 0.95f)
        WeightedShimmerTape(weight = 0.85f)
        WeightedShimmerTape(weight = 0.9f)
        WeightedShimmerTape(weight = 0.85f)
        WeightedShimmerTape(weight = 0.9f)
        WeightedShimmerTape(weight = 0.95f)
    }
}

