package care.visify.ui.kit.order.items

import androidx.compose.foundation.lazy.LazyListScope
import care.visify.ui.kit.containers.VisifyColumn
import care.visify.ui.kit.containers.cellitem.TextHintInfoCellItem

fun LazyListScope.dateTimeOrderItem(
    date: String,
    time: String,
    isDateEnable: Boolean = true,
    isTimeEnable: Boolean = true,
    onDateClick: () -> Unit = {},
    onTimeClick: () -> Unit = {},
) {
    item {
        VisifyColumn {
            TextHintInfoCellItem(
                text = date,
                hint = "Дата",
                hasDivider = true,
                isEnable = isDateEnable,
                onClick = onDateClick
            )
            TextHintInfoCellItem(
                text = time,
                hint = "Время",
                hasDivider = false,
                isEnable = isTimeEnable,
                onClick = onTimeClick
            )
        }
    }
}