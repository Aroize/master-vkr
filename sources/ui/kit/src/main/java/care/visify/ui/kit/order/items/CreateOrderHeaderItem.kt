package care.visify.ui.kit.order.items

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.R
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.microSecondary
import care.visify.ui.kit.theme.values.subheaderPrimary


fun LazyListScope.createOrderHeaderItem(
    orgName: String,
) {
    item {
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = orgName,
            style = VisifyTheme.visifyTypography.microSecondary
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = stringResource(id = R.string.create_order_header_sign),
            style = VisifyTheme.visifyTypography.subheaderPrimary
        )
    }
}