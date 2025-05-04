package care.visify.ui.kit.order.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.components.map.MapItem
import care.visify.ui.kit.components.map.MapPoint
import care.visify.ui.kit.containers.VisifyColumn
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.mainCellTertiary
import care.visify.ui.kit.theme.values.mainTextPrimary
import care.visify.ui.kit.theme.values.miniTertiary
import care.visify.ui.kit.theme.values.subheaderPrimary
import care.visify.ui.kit.util.asBgColor
import care.visify.ui.models.order.DetailedOrderUiModel


fun LazyListScope.orderMapItem(
    order: DetailedOrderUiModel,
) {
    item {
        VisifyColumn {
            Text(
                text = order.orgName,
                style = VisifyTheme.visifyTypography.subheaderPrimary,
                modifier = Modifier
                    .padding(start = 16.dp, top = 20.dp)
            )

            Text(
                text = order.orgAddress,
                style = VisifyTheme.visifyTypography.mainTextPrimary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 20.dp)
            )

            order.orgDistance?.let { distance ->
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = distance,
                    style = VisifyTheme.visifyTypography.miniTertiary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(VisifyTheme.colors.frame.white)
                        .padding(horizontal = 16.dp)
                )
            }

            order.orgOperatingTime?.let { operatingTime ->
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = operatingTime,
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
                    .asBgColor()
                    .clip(RoundedCornerShape(bottomStart = 6.dp, bottomEnd = 6.dp))
            ) {

                if (order.orgLat.isFinite() && order.orgLon.isFinite()) {

                    val point = MapPoint(
                        lat = order.orgLat.toDouble(),
                        lon = order.orgLon.toDouble(),
                    )
                    MapItem(
                        cameraPosition = point,
                        modifier = Modifier
                            .fillMaxSize(),
                        marks = listOf(point)
                    )
                }
            }
        }
    }
}