package care.visify.ui.kit.components.item

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import care.visify.ui.kit.components.button.VisifyButton
import care.visify.ui.kit.components.button.VisifyButtonColors
import care.visify.ui.kit.components.button.VisifyButtonStyle
import care.visify.ui.kit.theme.VisifyTheme


fun LazyListScope.sendMessageItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    item {
        VisifyButton(
            label = "Написать сообщение",
            onClick = onClick,
            buttonStyle = VisifyButtonStyle.START,
            colors = VisifyButtonColors.white().copy(
                contentColor = VisifyTheme.colors.label.active
            )
        )
    }
}