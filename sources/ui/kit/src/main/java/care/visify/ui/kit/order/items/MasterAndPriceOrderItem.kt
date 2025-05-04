package care.visify.ui.kit.order.items

import androidx.compose.foundation.lazy.LazyListScope
import care.visify.ui.kit.containers.VisifyColumn
import care.visify.ui.kit.containers.cellitem.TextHintInfoCellItem

fun LazyListScope.masterAndPriceOrderItem(
    master: String,
    price: String,
    isEnable: Boolean = true,
) {
    item {
        VisifyColumn {
            TextHintInfoCellItem(
                text = master,
                hint = "Мастер",
                hasDivider = true,
                isEnable = isEnable
            )
            TextHintInfoCellItem(
                text = price,
                hint = "Стоимость",
                hasDivider = false,
                isEnable = isEnable
            )
        }
    }
}