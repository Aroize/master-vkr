package care.visify.ui.kit.detailed.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import care.visify.core.image.Image
import care.visify.ui.kit.components.button.VisifyTextButton
import care.visify.ui.kit.components.image.VisifyImageById
import care.visify.ui.kit.containers.VisifyColumn
import care.visify.ui.kit.imageviewer.ImageViewerContainer
import care.visify.ui.kit.overlay.container.OverlayContainerStateImpl
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.subheaderPrimary
import care.visify.ui.kit.util.clickableNoInteraction
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

@Composable
fun Certificates(
    certificates: PersistentList<Image>,
    onShowAllCertificates: () -> Unit,
) {
    if (certificates.isEmpty()) return

    Spacer(modifier = Modifier.height(12.dp))

    VisifyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = VisifyTheme.padding._16dp),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Сертификаты",
                style = VisifyTheme.visifyTypography.subheaderPrimary,
                modifier = Modifier
            )
            VisifyTextButton(
                text = "Все",
                onClick = onShowAllCertificates
            )
        }

        var selectedImage by remember { mutableIntStateOf(0) }
        val imageContainerState = remember { OverlayContainerStateImpl() }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            itemsIndexed(certificates) { idx, imageId ->
                Box(
                    modifier = Modifier
                        .size(130.dp, 90.dp)
                        .clickableNoInteraction {
                            selectedImage = idx
                            imageContainerState.show()
                        }
                ) {
                    VisifyImageById(
                        model = imageId,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(4.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }

        ImageViewerContainer(
            containerState = imageContainerState,
            images = certificates.toPersistentList(),
            startIndex = selectedImage
        )
    }
}