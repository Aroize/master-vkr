package care.visify.ui.kit.order.items

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.headerLargePrimary

fun LazyListScope.categoryHeaderOrderItem(
    category: String,
) {
    item {
        Text(
            text = category,
            style = VisifyTheme.visifyTypography.headerLargePrimary,
            modifier = Modifier.padding(top = 12.dp, bottom = 8.dp)
        )
    }
}