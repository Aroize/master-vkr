package care.visify.ui.kit.components.nav.top

import androidx.compose.runtime.Composable
import care.visify.ui.icons.IconsR

@Composable
fun TopBarClose(
    title : String? = null,
    actionText: String? = null,
    onActionClick: (() -> Unit)? = null,
    onCloseClick: () -> Unit,
) {
    TopBarActionInternal(
        iconRes = IconsR.ic_close_24,
        title = title,
        onCloseClick = onCloseClick,
        actionText = actionText,
        onActionClick = onActionClick
    )
}
