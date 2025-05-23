package care.visify.ui.kit.components.item

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.headerLargePrimary


fun LazyListScope.headerItem(
    modifier: Modifier = Modifier,
    text: String,
) {
    item {
        Text(
            text = text,
            style = VisifyTheme.visifyTypography.headerLargePrimary,
            modifier = modifier,
        )
    }
}