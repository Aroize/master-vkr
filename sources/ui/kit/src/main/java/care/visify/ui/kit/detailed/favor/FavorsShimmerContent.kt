package care.visify.ui.kit.detailed.favor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.components.shimmer.TapeShimmer
import care.visify.ui.kit.containers.cellitem.SelectedState
import care.visify.ui.kit.containers.cellitem.VisifyCellItem
import care.visify.ui.kit.util.cell
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer

@Composable
fun FavorsShimmerContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .cell()
    ) {
        FavorShimmer(
            shape = RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp),
            hasDivider = true
        )
        FavorShimmer(
            shape = RectangleShape,
            hasDivider = true
        )
        FavorShimmer(
            shape = RoundedCornerShape(bottomStart = 6.dp, bottomEnd = 6.dp),
            hasDivider = false
        )
    }
}

@Composable
private fun FavorShimmer(
    shape: Shape,
    hasDivider: Boolean,
) {
    VisifyCellItem(
        shape = shape,
        hasDivider = hasDivider,
        selectedState = SelectedState.Gone,
        modifier = Modifier.fillMaxWidth()
    ) { _ ->
        val (topRef, bottomRef, endRef) = createRefs()
        val shimmer = rememberShimmer(shimmerBounds = ShimmerBounds.Window)
        TapeShimmer(
            size = DpSize(144.dp, 8.dp),
            shimmer = shimmer,
            modifier = Modifier.constrainAs(topRef) {
                top.linkTo(parent.top, margin = 19.dp)
                start.linkTo(parent.start)
            }
        )
        TapeShimmer(
            size = DpSize(100.dp, 8.dp),
            shimmer = shimmer,
            modifier = Modifier.constrainAs(bottomRef) {
                top.linkTo(topRef.bottom, margin = 12.dp)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom, margin = 19.dp)
            }
        )
        TapeShimmer(
            size = DpSize(32.dp, 8.dp),
            shimmer = shimmer,
            modifier = Modifier.constrainAs(endRef) {
                top.linkTo(topRef.top)
                end.linkTo(parent.end)
            }
        )
    }
}
