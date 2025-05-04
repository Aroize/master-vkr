package care.visify.ui.kit.detailed.portfolio

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.components.indicator.ImageIndicatorColors
import care.visify.ui.kit.containers.VisifyDraggableContainer
import care.visify.ui.kit.detailed.feedback.ExtendedReviewItem
import care.visify.ui.kit.detailed.feedback.ExtendedReviewItemsColors
import care.visify.ui.kit.detailed.master.MasterItem
import care.visify.ui.kit.imageviewer.ImageViewerContent
import care.visify.ui.kit.imageviewer.ImageViewerState
import care.visify.ui.kit.overlay.AnimatedOverlayContainer
import care.visify.ui.kit.overlay.container.OverlayContainerState
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.microPrimary
import care.visify.ui.kit.theme.values.microWhite
import care.visify.ui.kit.util.asBgColor
import care.visify.ui.kit.util.clickableNoInteraction
import care.visify.ui.models.portfolio.PortfolioDetailedUiModel
import kotlinx.collections.immutable.persistentListOf

@Composable
fun PortfolioDetailedItem(
    state: PortfolioDetailedUiModel,
    container: OverlayContainerState,
    onDismiss: () -> Unit,
    onMasterClicked: (Int) -> Unit
) {
    AnimatedOverlayContainer(
        onDismissRequest = onDismiss,
        containerState = container
    ) {

        var cardSize by remember { mutableStateOf(IntSize(0, 0)) }
        val offsetY = remember { Animatable(cardSize.height.toFloat()) }

        val statusBarOffset = with(LocalDensity.current) {
            WindowInsets.statusBars.getTop(this).toDp()
        }

        Card(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = offsetY.value.dp)
                .onSizeChanged { cardSize = it }
                .padding(top = statusBarOffset),
            shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {

            VisifyDraggableContainer(
                modifier = Modifier,
                offsetY = offsetY,
                onDismiss = onDismiss
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .asBgColor()
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentPadding = PaddingValues(top = 32.dp)
                    ) {
                        item {
                            ImageViewerContent(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f),
                                state = remember {
                                    ImageViewerState(
                                        startIndex = 0,
                                        items = state.images
                                    )
                                },
                                isIndicatorInside = false,
                                imageLabel = { id ->
                                    if (state.reviewItem?.wearing?.images?.contains(id) == true) {
                                        Text(
                                            text = "После носки",
                                            style = VisifyTheme.visifyTypography.microPrimary,
                                            modifier = Modifier
                                                .padding(top = 8.dp, end = 8.dp)
                                                .background(
                                                    VisifyTheme.colors.frame.white,
                                                    RoundedCornerShape(4.dp)
                                                )
                                                .padding(horizontal = 8.dp, vertical = 3.dp)
                                                .align(Alignment.TopEnd)
                                        )
                                    }
                                },
                                imageIndicatorColors = ImageIndicatorColors(
                                    VisifyTheme.colors.frame.active,
                                    VisifyTheme.colors.frame.disabled
                                )
                            )
                        }

                        item {
                            Spacer(modifier = Modifier.height(24.dp))

                            Text(
                                text = state.favor,
                                style = VisifyTheme.visifyTypography.microWhite,
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .background(
                                        VisifyTheme.colors.frame.active,
                                        RoundedCornerShape(4.dp)
                                    )
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        item {
                            state.reviewItem?.apply {
                                ExtendedReviewItem(
                                    item = copy(
                                        review = review.copy(images = persistentListOf()),
                                        wearing = wearing?.copy(images = persistentListOf())
                                    ),
                                    colors = ExtendedReviewItemsColors.light()
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                            }
                        }

                        item {
                            MasterItem(
                                master = state.master,
                                shape = RoundedCornerShape(6.dp),
                                hasDivider = false,
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .clickableNoInteraction {
                                        onMasterClicked(state.master.id)
                                    }
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }

                }
            }
        }
    }
}