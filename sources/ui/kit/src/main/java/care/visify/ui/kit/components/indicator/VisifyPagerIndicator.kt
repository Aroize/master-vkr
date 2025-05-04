package care.visify.ui.kit.components.indicator

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.theme.VisifyTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BoxScope.VisifyPagerIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    imageIndicatorColors: ImageIndicatorColors = ImageIndicatorColors(
        VisifyTheme.colors.frame.white,
        VisifyTheme.colors.frame.disabled
    ),
) {
    ImageIndicator(
        pagerState = pagerState,
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .align(Alignment.BottomCenter),
        imageIndicatorColors = imageIndicatorColors
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VisifyPagerIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    imageIndicatorColors: ImageIndicatorColors = ImageIndicatorColors(
        VisifyTheme.colors.frame.white,
        VisifyTheme.colors.frame.disabled
    ),
) {
    ImageIndicator(
        pagerState = pagerState,
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        imageIndicatorColors
    )
}

data class ImageIndicatorColors(
    val activeColor: Color,
    val disabledColor: Color,
) {
    companion object {
        @Composable
        fun default(): ImageIndicatorColors {
            return ImageIndicatorColors(
                VisifyTheme.colors.frame.white,
                VisifyTheme.colors.frame.disabled
            )
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    imageIndicatorColors: ImageIndicatorColors,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration)
                imageIndicatorColors.activeColor
            else
                imageIndicatorColors.disabledColor
            val width = if (pagerState.currentPage == iteration)
                12.dp
            else
                2.dp
            Box(
                modifier = Modifier
                    .animateContentSize()
                    .padding(2.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(color)
                    .size(width, 2.dp)
            )
        }
    }
}