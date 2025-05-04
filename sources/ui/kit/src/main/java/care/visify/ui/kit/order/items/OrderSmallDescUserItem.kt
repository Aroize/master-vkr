package care.visify.ui.kit.order.items

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import care.visify.ui.kit.user.UserSmallDescItem
import care.visify.ui.models.user.UserUiModel


fun LazyListScope.orderSmallDescClientItem(
    user: UserUiModel,
) {
    item {
        UserSmallDescItem(
            modifier = Modifier.fillMaxWidth(),
            avatar = user.avatar,
            title = user.name,
            desc = "Клиент"
        )
    }
}