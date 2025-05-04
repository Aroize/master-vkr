package care.visify.ui.kit.components.shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.theme.VisifyTheme
import com.valentinilk.shimmer.shimmer

@Composable
fun WeightedShimmerTape(weight: Float) {
    Box(
        modifier = Modifier
            .fillMaxWidth(fraction = weight)
            .height(8.dp)
            .shimmer()
            .background(VisifyTheme.colors.frame.dialogOverlay)
    )
}