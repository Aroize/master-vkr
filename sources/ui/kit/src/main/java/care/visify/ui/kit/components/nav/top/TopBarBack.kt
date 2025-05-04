package care.visify.ui.kit.components.nav.top

import androidx.compose.runtime.Composable
import care.visify.ui.icons.IconsR


@Composable
fun TopBarBack(
    title : String? = null,
    actionText: String? = null,
    onActionClick: (() -> Unit)? = null,
    onBackClick: () -> Unit,
) {
    TopBarActionInternal(
        iconRes = IconsR.ic_back_24,
        onCloseClick = onBackClick,
        title = title,
        actionText = actionText,
        onActionClick = onActionClick
    )
}
