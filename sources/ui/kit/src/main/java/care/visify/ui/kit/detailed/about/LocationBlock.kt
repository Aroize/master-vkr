package care.visify.ui.kit.detailed.about

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.components.button.VisifyTextButton
import care.visify.ui.kit.components.map.MapItem
import care.visify.ui.kit.components.map.MapPoint
import care.visify.ui.kit.containers.VisifyColumn
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.mainCellTertiary
import care.visify.ui.kit.theme.values.mainTextPrimary
import care.visify.ui.kit.theme.values.miniTertiary
import care.visify.ui.kit.theme.values.subheaderPrimary
import care.visify.ui.models.location.LocationUiModel


@Composable
fun LocationBlock(
    about: LocationUiModel,
    onShowOnMapClicked: () -> Unit,
) {
    Spacer(modifier = Modifier.height(12.dp))
    VisifyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = VisifyTheme.padding._16dp)
    ) {
        Text(
            text = "Адрес",
            style = VisifyTheme.visifyTypography.subheaderPrimary,
            modifier = Modifier
                .padding(start = 16.dp, top = 20.dp)
        )

        Text(
            text = about.address,
            style = VisifyTheme.visifyTypography.mainTextPrimary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 20.dp)
        )

        about.distance?.let {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = about.distance!!,
                style = VisifyTheme.visifyTypography.miniTertiary,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(VisifyTheme.colors.frame.white)
                    .padding(horizontal = 16.dp)
            )
        }

        about.operatingTime?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = about.operatingTime!!,
                style = VisifyTheme.visifyTypography.mainCellTertiary,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(VisifyTheme.colors.frame.white)
                    .padding(horizontal = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 6.dp, bottomEnd = 6.dp))
        ) {

            if (about.lat.isFinite() && about.lat.isFinite()) {
                val point = MapPoint(
                    lat = about.lat.toDouble(),
                    lon = about.lon.toDouble(),
                )

                MapItem(
                    cameraPosition = point,
                    modifier = Modifier
                        .fillMaxSize(),
                    marks = listOf(point)
                )
            }

            VisifyTextButton(
                text = "Смотреть на карте",
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.BottomStart),
                onClick = onShowOnMapClicked
            )
        }
    }
}