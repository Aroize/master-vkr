package care.visify.ui.kit.detailed.feedback

import androidx.compose.foundation.layout.Arrangement
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
import care.visify.ui.kit.components.shimmer.WeightedShimmerTape
import care.visify.ui.kit.containers.cellitem.SelectedState
import care.visify.ui.kit.containers.cellitem.VisifyCellItem
import care.visify.ui.kit.util.cell
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer

@Composable
fun FeedbackShimmerContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .cell()
    ) {
        FeedbackShimmerItem(
            shape = RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp),
            hasDivider = true
        )
        FeedbackShimmerItem(
            shape = RectangleShape,
            hasDivider = true
        )
        FeedbackShimmerItem(
            shape = RoundedCornerShape(bottomStart = 6.dp, bottomEnd = 6.dp),
            hasDivider = false
        )
    }
}

@Composable
private fun FeedbackShimmerItem(
    shape: Shape,
    hasDivider: Boolean,
) {
    VisifyCellItem(
        shape = shape,
        hasDivider = hasDivider,
        selectedState = SelectedState.Gone
    ) { _ ->

        val shimmer = rememberShimmer(shimmerBounds = ShimmerBounds.Window)
        val (titleRef, ratingRef, linesRef) = createRefs()

        TapeShimmer(
            size = DpSize(132.dp, 8.dp),
            shimmer = shimmer,
            modifier = Modifier.constrainAs(titleRef) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(parent.start)
            }
        )

        TapeShimmer(
            size = DpSize(32.dp, 8.dp),
            shimmer = shimmer,
            modifier = Modifier.constrainAs(ratingRef) {
                top.linkTo(parent.top, margin = 16.dp)
                end.linkTo(parent.end)
            }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(linesRef) {
                    top.linkTo(titleRef.bottom, margin = 16.dp)
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                },
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            WeightedShimmerTape(weight = 1f)
            WeightedShimmerTape(weight = 0.95f)
            WeightedShimmerTape(weight = 0.98f)
            WeightedShimmerTape(weight = 0.85f)
        }
    }
}