package care.visify.ui.kit.components.shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import care.visify.ui.kit.theme.VisifyTheme
import com.valentinilk.shimmer.Shimmer
import com.valentinilk.shimmer.shimmer


@Composable
fun TapeShimmer(
    size: DpSize,
    shimmer: Shimmer,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(size)
            .shimmer(shimmer)
            .background(VisifyTheme.colors.frame.dialogOverlay)
    )
}

@Composable
fun TapeShimmer(
    shimmer: Shimmer,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .shimmer(shimmer)
            .background(VisifyTheme.colors.frame.dialogOverlay)
    )
}