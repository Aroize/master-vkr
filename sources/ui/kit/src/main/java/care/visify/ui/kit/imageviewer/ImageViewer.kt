package care.visify.ui.kit.imageviewer

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import care.visify.core.image.Image
import care.visify.ui.kit.components.image.VisifyImageById
import care.visify.ui.kit.components.indicator.ImageIndicatorColors
import care.visify.ui.kit.components.indicator.VisifyPagerIndicator
import care.visify.ui.kit.overlay.AnimatedOverlayContainer
import care.visify.ui.kit.overlay.container.OverlayContainerState
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.buttonWhite
import care.visify.ui.kit.util.clickableNoInteraction
import care.visify.ui.kit.util.dpInt
import care.visify.ui.kit.util.pagerTapSnap
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.collections.immutable.PersistentList

// ========= begin region

data class ImageViewerState(
    val startIndex: Int,
    val items: PersistentList<Image>,
)

// ========= end region

//TODO to image viewer module
@Composable
fun ImageViewerContainer(
    containerState: OverlayContainerState,
    images: PersistentList<Image>,
    startIndex: Int = 0,
) {
    AnimatedOverlayContainer(
        containerState = containerState,
        onDismissRequest = { containerState.hide() },
    ) {
        updateStatusBarWithColor(color = VisifyTheme.colors.frame.black)
        ImageViewer(
            container = containerState,
            state = ImageViewerState(
                startIndex = startIndex,
                items = images
            )
        )
    }
}

@Composable
fun ImageViewer(
    container: OverlayContainerState,
    state: ImageViewerState,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(VisifyTheme.colors.frame.black),
        horizontalAlignment = Alignment.End
    ) {

        Text(
            text = "Закрыть",
            style = VisifyTheme.visifyTypography.buttonWhite,
            modifier = Modifier
                .padding(top = 60.dp, end = 16.dp)
                .height(52.dp)
                .clickableNoInteraction { container.hide() },
            textAlign = TextAlign.Center
        )

        ImageViewerContent(
            state = state,
            modifier = Modifier
                .padding(bottom = 44.dp)
                .weight(1f)
                .fillMaxWidth()
                .background(VisifyTheme.colors.frame.black),
            isIndicatorInside = false,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageViewerContent(
    modifier: Modifier = Modifier,
    isIndicatorInside: Boolean = true,
    state: ImageViewerState,
    imageLabel: @Composable BoxScope.(Image) -> Unit = { },
    imageIndicatorColors: ImageIndicatorColors =
        ImageIndicatorColors.default(),
    ) {
    val pagerState = rememberPagerState(
        pageCount = { state.items.size },
        initialPage = state.startIndex
    )

    val ctx = LocalContext.current
    val cornerRadius = remember { 4.dpInt(ctx) }

    val content: @Composable (Modifier, Modifier) -> Unit = { rootMd, indicatorMd ->
        HorizontalPager(
            state = pagerState,
            modifier = rootMd,
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .pagerTapSnap(pagerState)
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                VisifyImageById(
                    model = state.items[page],
                    modifier = Modifier
                        //.zoomable() не работает пейджер, но в целом похуй, зум пока не нужен
                        .align(Alignment.Center)
                        .clip(RoundedCornerShape(4.dp)),
                    shimmerModifier = Modifier.fillMaxSize(),
                    requestBuilderTransform = {
                        it.transform(RoundedCorners(cornerRadius))
                    },
                    contentScale = ContentScale.FillWidth
                )
                imageLabel(this, state.items[page])
            }
        }
        VisifyPagerIndicator(pagerState = pagerState, indicatorMd, imageIndicatorColors)
    }

    when {
        isIndicatorInside -> Box(
            modifier = modifier,
            content = {
                content(
                    Modifier
                        .fillMaxSize()
                        .align(Alignment.Center),
                    Modifier
                        .align(Alignment.BottomCenter)
                )
            }
        )

        else -> Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            content = {
                content(
                    Modifier.weight(1f),
                    Modifier
                )
            }
        )
    }
}