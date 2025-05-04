package care.visify.ui.kit.components.item

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

fun LazyListScope.verticalSpacerItem(
    height: Dp,
) {
    item {
        Spacer(Modifier.height(height))
    }
}