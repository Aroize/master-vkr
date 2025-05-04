package care.visify.ui.kit.detailed.portfolio

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import care.visify.ui.icons.IconsR
import care.visify.ui.kit.components.image.VisifyImageById
import care.visify.ui.kit.components.shimmer.TapeShimmer
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.util.clickableNoInteraction
import care.visify.ui.models.order.OrderUiModel
import com.valentinilk.shimmer.Shimmer
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer

fun LazyListScope.portfolioImagesGrid(
    chunks: List<List<OrderUiModel>>,
    onImageClick: (Int) -> Unit,
) {

    itemsIndexed(chunks) { index, orderRow ->

        val shimmer = rememberShimmer(shimmerBounds = ShimmerBounds.Window)

        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier
                .background(VisifyTheme.colors.frame.grey)
        ) {
            orderRow.forEach { order ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                ) {

                    MaybeMissingCover(order, shimmer, onImageClick)

                    if (order.feedback?.wearing != null) {
                        Image(
                            painter = painterResource(id = IconsR.ic_clock_10),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(5.dp)
                                .background(
                                    VisifyTheme.colors.label.active,
                                    RoundedCornerShape(2.dp)
                                )
                                .padding(1.5.dp)
                        )
                    }
                }
            }
            repeat(3 - orderRow.size) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .background(VisifyTheme.colors.frame.grey)
                )
            }
        }
        if (index != chunks.lastIndex) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(VisifyTheme.colors.frame.grey)
            )
        }
    }

    item { Spacer(modifier = Modifier.height(24.dp)) }

}

@Composable
private fun MaybeMissingCover(
    order: OrderUiModel,
    shimmer: Shimmer,
    onImageClick: (Int) -> Unit,
) {
    if (order.covers.isNotEmpty()) {
        VisifyImageById(
            model = order.covers.first(),
            modifier = Modifier
                .fillMaxSize()
                .clickableNoInteraction { onImageClick(order.id) },
            requestBuilderTransform = { it.centerCrop() }
        )
    } else {
        TapeShimmer(
            shimmer = shimmer,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}