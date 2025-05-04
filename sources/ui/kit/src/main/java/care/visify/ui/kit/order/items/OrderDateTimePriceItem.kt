package care.visify.ui.kit.order.items

import androidx.compose.foundation.lazy.LazyListScope
import care.visify.ui.kit.containers.VisifyColumn
import care.visify.ui.kit.containers.cellitem.TextHintInfoCellItem

fun LazyListScope.orderDateTimePriceItem(
    date: String,
    time: String,
    price: String,
) {
    item {
        VisifyColumn {
            TextHintInfoCellItem(
                text = date,
                hint = "Дата",
                hasDivider = true,
                isEnable = true,
            )
            TextHintInfoCellItem(
                text = time,
                hint = "Время",
                hasDivider = true,
                isEnable = true
            )
            TextHintInfoCellItem(
                text = price,
                hint = "Стоимость",
                hasDivider = false,
                isEnable = true
            )
        }
    }
}