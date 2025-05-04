package care.visify.ui.kit.detailed

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.collapsing.SnappingNestedScrollConnection
import care.visify.ui.kit.collapsing.VisifyCollapsingContainerState
import care.visify.ui.kit.collapsing.VisifySnappingScaffold
import care.visify.ui.kit.collapsing.VisifySnappingScaffoldState
import care.visify.ui.kit.collapsing.rememberCollapsingContainerState
import care.visify.ui.kit.util.asBgColor


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailedScreen(
    underlayHeight: Dp,
    pages: List<LazyListState>,
    topBar: @Composable (VisifyCollapsingContainerState, NestedScrollConnection, PagerState) -> Unit,
    underlay: @Composable (Animatable<Float, AnimationVector1D>) -> Unit,
    bottomBar: @Composable () -> Unit,
    content: @Composable (Int, LazyListState, SnappingNestedScrollConnection) -> Unit,
    other: @Composable (PagerState) -> Unit,
) {
    var cardSize by remember { mutableStateOf(IntSize(0, 0)) }
    val offsetY = remember { Animatable(cardSize.height.toFloat()) }

    val statusBarOffset = with(LocalDensity.current) {
        WindowInsets.statusBars.getTop(this).toDp()
    }

    val pagerState = rememberPagerState(
        initialPage = pages.lastIndex,
        pageCount = { pages.size }
    )
    val actualList by remember {
        derivedStateOf { pages[pagerState.currentPage] }
    }

    val collapsingState = rememberCollapsingContainerState()
    val snappingState = remember {
        VisifySnappingScaffoldState(
            canConsumeScroll = derivedStateOf {
                actualList.firstVisibleItemIndex != 0 || actualList.firstVisibleItemScrollOffset != 0
            },
            topStateScrollConsumer = { delta ->
                collapsingState.consumeVerticalScroll(delta)
            }
        )
    }

    Card(
        modifier = Modifier
            .padding(top = statusBarOffset)
            .fillMaxSize()
            .offset(y = offsetY.value.dp)
            .onSizeChanged { cardSize = it },
        shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        VisifySnappingScaffold(
            overlayContainerHeight = underlayHeight,
            snappingState = snappingState,
            topBar = {
                topBar(collapsingState, snappingState.nestedScrollConnection, pagerState)
            },
            bottomBar = {
                bottomBar()
            },
            underlay = {
                underlay(offsetY)
            },
            content = { paddingValues ->
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.padding(paddingValues)
                ) { page ->
                    Box(
                        modifier = Modifier.fillMaxSize()
                            .asBgColor(),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        val list = pages[page]
                        content(page, list, snappingState.nestedScrollConnection)
                    }
                }
            }
        )
    }

    other(pagerState)
}
