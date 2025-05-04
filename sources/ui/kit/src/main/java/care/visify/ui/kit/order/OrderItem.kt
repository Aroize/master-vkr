package care.visify.ui.kit.order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.R
import care.visify.ui.kit.components.divider.VisifyDivider
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.microActive
import care.visify.ui.kit.theme.values.microPrimary
import care.visify.ui.kit.theme.values.microSecondary
import care.visify.ui.models.order.MarketOrderStatus
import care.visify.ui.models.order.MarketOrderUiModel

@Composable
fun OrderItem(
    order: MarketOrderUiModel,
    modifier: Modifier = Modifier,
    onMoreClicked: (Int) -> Unit,
    onDetailsClicked: (Int) -> Unit,
) {
    Column(
        modifier = modifier
            .background(VisifyTheme.colors.frame.white, RoundedCornerShape(6.dp))
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        VisitHeader(
            name = order.name,
            isMoreShown = order.isArchive.not(),
            onMoreClicked = { onMoreClicked(order.id) }
        )
        VisitDate(date = order.date)
        Spacer(modifier = Modifier.height(16.dp))
        OrderStatus(creationDate = order.creationDate, status = order.status)
        Spacer(modifier = Modifier.height(16.dp))
        if (order.isArchive.not()) {
            VisifyDivider()
            VisitDetails(onDetailsClicked = { onDetailsClicked(order.id) })
        }
    }
}


@Composable
private fun OrderStatus(
    creationDate: String,
    status: MarketOrderStatus,
    modifier: Modifier = Modifier,
) {
    Row {
        Text(
            text = when (status) {
                MarketOrderStatus.ACTIVE ->
                    stringResource(id = R.string.visits_active)

                MarketOrderStatus.STOPPED ->
                    stringResource(id = R.string.visits_search_stopped)

                MarketOrderStatus.CLOSED ->
                    stringResource(id = R.string.visits_closed)
            },
            style = when (status) {
                MarketOrderStatus.ACTIVE ->
                    VisifyTheme.visifyTypography.microActive

                MarketOrderStatus.STOPPED ->
                    VisifyTheme.visifyTypography.microSecondary

                MarketOrderStatus.CLOSED ->
                    VisifyTheme.visifyTypography.microPrimary

            },
            modifier = modifier
        )
        Text(
            text = ", $creationDate",
            style = VisifyTheme.visifyTypography.microPrimary,
            modifier = modifier
        )
    }
}


