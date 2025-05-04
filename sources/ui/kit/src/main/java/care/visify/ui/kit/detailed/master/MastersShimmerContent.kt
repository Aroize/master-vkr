package care.visify.ui.kit.detailed.master

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility
import care.visify.ui.kit.components.divider.VisifyDivider
import care.visify.ui.kit.components.shimmer.TapeShimmer
import care.visify.ui.kit.containers.cellitem.SelectedState
import care.visify.ui.kit.containers.cellitem.VisifyCellItem
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.util.cell
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer

@Composable
fun MastersShimmerContent() {
    Column(
        modifier = Modifier.fillMaxWidth()
            .cell()
    ) {
        MasterShimmerItem(
            shape = RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp),
            hasDivider = true
        )
        MasterShimmerItem(
            shape = RectangleShape,
            hasDivider = true
        )
        MasterShimmerItem(
            shape = RectangleShape,
            hasDivider = true
        )
        MasterShimmerItem(
            shape = RoundedCornerShape(bottomStart = 6.dp, bottomEnd = 6.dp),
            hasDivider = false
        )
    }
}

@Composable
private fun MasterShimmerItem(
    shape: Shape,
    hasDivider: Boolean
) {
    VisifyCellItem(
        shape = shape,
        hasDivider = false,
        selectedState = SelectedState.Gone,
        modifier = Modifier.fillMaxWidth()
    ) { _ ->

        val shimmer = rememberShimmer(shimmerBounds = ShimmerBounds.Window)

        val (avatarRef, nameRef, professionRef, ratingRef, divRef) = createRefs()

        Box(
            modifier = Modifier
                .size(48.dp)
                .shimmer(shimmer)
                .background(VisifyTheme.colors.frame.dialogOverlay, CircleShape)
                .constrainAs(avatarRef) {
                    top.linkTo(parent.top, margin = 8.dp)
                    bottom.linkTo(parent.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                }
        )

        TapeShimmer(
            size = DpSize(132.dp, 8.dp),
            shimmer = shimmer,
            modifier = Modifier.constrainAs(nameRef) {
                top.linkTo(avatarRef.top, margin = 11.dp)
                start.linkTo(avatarRef.end, margin = 12.dp)
            }
        )

        TapeShimmer(
            size = DpSize(100.dp, 8.dp),
            shimmer = shimmer,
            modifier = Modifier.constrainAs(professionRef) {
                top.linkTo(nameRef.bottom, margin = 12.dp)
                start.linkTo(nameRef.start)
            }
        )

        TapeShimmer(
            size = DpSize(32.dp, 8.dp),
            shimmer = shimmer,
            modifier = Modifier.constrainAs(ratingRef) {
                top.linkTo(nameRef.top)
                end.linkTo(parent.end)
            }
        )

        VisifyDivider(
            modifier = Modifier.constrainAs(divRef) {
                start.linkTo(nameRef.start)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
                visibility = when (hasDivider) {
                    true -> Visibility.Visible
                    else -> Visibility.Gone
                }
            }
        )
    }
}