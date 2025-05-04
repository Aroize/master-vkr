package care.visify.ui.kit.order.items

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import care.visify.ui.kit.containers.VisifyBox
import care.visify.ui.kit.containers.cellitem.TextHintInfoCellItem

fun LazyListScope.commentOrderItem(
    comment: String?,
    isEnable: Boolean = true,
) {
    item {
        VisifyBox {
            TextHintInfoCellItem(
                text = comment ?: "-",
                hint = "Комментарий",
                hasDivider = false,
                modifier = Modifier.fillMaxWidth(),
                isEnable = isEnable,
            )
        }
    }
}