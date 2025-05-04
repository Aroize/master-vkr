package care.visify.ui.kit.order.items

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import care.visify.ui.kit.user.UserSmallDescItem
import care.visify.ui.models.master.MasterUiModel

fun LazyListScope.orderSmallDescMasterItem(
    master: MasterUiModel,
) {
    item {
        UserSmallDescItem(
            modifier = Modifier.fillMaxWidth(),
            avatar = master.avatar,
            title = "${master.name} ${master.surname.orEmpty()}",
            desc = master.profession
        )
    }
}