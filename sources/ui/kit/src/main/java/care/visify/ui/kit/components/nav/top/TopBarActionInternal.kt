package care.visify.ui.kit.components.nav.top

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import care.visify.core.util.multiLet
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.buttonActive
import care.visify.ui.kit.util.asBgColor
import care.visify.ui.kit.util.clickableNoInteraction

@Composable
internal fun TopBarActionInternal(
    @DrawableRes
    iconRes: Int,
    onCloseClick: () -> Unit,
    title: String? = null,
    actionText: String? = null,
    onActionClick: (() -> Unit)? = null,
) {
    Box(
        modifier = Modifier
            .asBgColor()
            .height(52.dp)
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = "Back",
            modifier = Modifier
                .clickableNoInteraction(onCloseClick)
                .align(Alignment.CenterStart)
        )

        title?.let {
            Text(
                text = it,
                style = VisifyTheme.visifyTypography.navHeader,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }

        multiLet(actionText, onActionClick) { text, click ->
            Text(
                text = text,
                style = VisifyTheme.visifyTypography.buttonActive,
                modifier = Modifier
                    .clickableNoInteraction(click)
                    .align(Alignment.CenterEnd)
            )
        }
    }
}