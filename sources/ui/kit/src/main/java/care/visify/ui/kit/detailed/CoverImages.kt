package care.visify.ui.kit.detailed

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import care.visify.core.image.Image
import care.visify.ui.icons.IconsR
import care.visify.ui.kit.components.image.VisifyImageById
import care.visify.ui.kit.components.image.rememberGlideBitmap
import care.visify.ui.kit.components.indicator.VisifyPagerIndicator
import care.visify.ui.kit.imageviewer.ImageViewerContainer
import care.visify.ui.kit.overlay.container.OverlayContainerStateImpl
import care.visify.ui.kit.util.clickableNoInteraction
import care.visify.ui.kit.util.pagerTapSnap
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.collections.immutable.PersistentList

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BoxScope.CoverImages(
    logo: Image,
    images: PersistentList<Image>,
    onShareClick: () -> Unit,
) {

    var selectedImage by remember { mutableStateOf(0) }
    val pagerState = rememberPagerState(pageCount = { images.size })

    val imageContainerState = remember { OverlayContainerStateImpl() }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
    ) { page ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .pagerTapSnap(pagerState) {
                    selectedImage = page
                    imageContainerState.show()
                }
        ) {

            val image = rememberGlideBitmap(images[page])
            val blurred = rememberGlideBitmap(images[page]) {
                it.transform(BlurTransformation(20))
            }

            Image(
                bitmap = blurred.asImageBitmap(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Image(
                image.asImageBitmap(),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.Center)
            )
        }
    }

    Box(
        modifier = Modifier
            .padding(start = 16.dp, bottom = 20.dp)
            .align(Alignment.BottomStart)
    ) {
        VisifyImageById(
            model = logo,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(4.dp))
        )
    }

    Image(
        painter = painterResource(id = IconsR.ic_sharing_24),
        contentDescription = null,
        modifier = Modifier
            .padding(end = 16.dp, top = 28.dp)
            .align(Alignment.TopEnd)
            .clickableNoInteraction(onShareClick)
    )

    VisifyPagerIndicator(
        pagerState = pagerState,
        modifier = Modifier.padding(bottom = 8.dp)
    )

    ImageViewerContainer(
        containerState = imageContainerState,
        images = images,
        startIndex = selectedImage
    )
}
