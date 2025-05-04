package care.visify.ui.kit.detailed.about

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import care.visify.ui.icons.IconsR
import care.visify.ui.kit.components.button.VisifyButton
import care.visify.ui.kit.components.button.VisifyButtonColors

@Composable
fun WriteMessageButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    VisifyButton(
        label = "Написать сообщение",
        onClick = onClick,
        colors = VisifyButtonColors.white(),
        icon = {
            Image(
                painter = painterResource(id = IconsR.ic_nav_chat_24),
                contentDescription = "Message"
            )
        },
        modifier = modifier

    )
}