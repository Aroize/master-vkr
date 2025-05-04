package care.visify.ui.kit.components.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.components.indicator.VisifyProgressIndicator
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.util.clickableNoInteraction

@Composable
fun VisifyIconButton(
    @DrawableRes
    iconRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    colors: VisifyButtonColors = VisifyButtonColors.whiteActive(),
    buttonHeight: Dp = 56.dp,
) {
    val containerColor = if (enabled) colors.containerColor else colors.disabledContainerColor
    val contentColor = if (enabled) colors.contentColor else colors.disabledContentColor

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .height(buttonHeight)
            .clickableNoInteraction {
                if (enabled.not() || isLoading) return@clickableNoInteraction
                onClick()
            }
            .background(
                color = containerColor,
                shape = RoundedCornerShape(6.dp)
            )
            .padding(horizontal = 16.dp),
    ) {
        when {
            isLoading -> {
                VisifyProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = VisifyTheme.colors.frame.white,
                    strokeWidth = 2.dp
                )
            }

            else -> {
                Icon(
                    painter = painterResource(iconRes),
                    contentDescription = "Icon",
                    tint = contentColor
                )
            }
        }
    }
}