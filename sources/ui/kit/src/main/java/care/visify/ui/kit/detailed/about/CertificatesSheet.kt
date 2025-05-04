package care.visify.ui.kit.detailed.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import care.visify.core.image.Image
import care.visify.ui.kit.components.image.VisifyImageById
import care.visify.ui.kit.imageviewer.ImageViewerContainer
import care.visify.ui.kit.modal.VisifyModalBottomSheet
import care.visify.ui.kit.modal.VisifySheetState
import care.visify.ui.kit.overlay.container.OverlayContainerStateImpl
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.subheaderPrimary
import care.visify.ui.kit.util.clickableNoInteraction
import kotlinx.collections.immutable.PersistentList

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CertificatesSheet(state: VisifySheetState<PersistentList<Image>>) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val imageContainerState = remember { OverlayContainerStateImpl() }

    VisifyModalBottomSheet(visifySheetState = state) {
        Text(
            text = "Сертификаты",
            style = VisifyTheme.visifyTypography.subheaderPrimary,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            state.unsafeData.forEachIndexed { index, imageId ->
                val mW = with(LocalDensity.current) { 100.dp.toPx().toInt() }
                val mH = with(LocalDensity.current) { 60.dp.toPx().toInt() }
                VisifyImageById(
                    model = imageId,
                    modifier = Modifier
                        .padding(vertical = 1.dp)
                        .clickableNoInteraction {
                            state.hide()
                            selectedIndex = index
                            imageContainerState.show()
                        },
                    contentScale = ContentScale.Inside,
                    requestBuilderTransform = { it.override(mW, mH) }
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
    }

    val images = state.data

    if (images != null) {
        ImageViewerContainer(
            containerState = imageContainerState,
            images = images,
            startIndex = selectedIndex
        )
    }
}