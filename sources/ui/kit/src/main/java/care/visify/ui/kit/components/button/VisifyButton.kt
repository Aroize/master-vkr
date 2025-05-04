package care.visify.ui.kit.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.components.indicator.VisifyProgressIndicator
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.util.clickableNoInteraction


@Composable
fun VisifyButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    icon: @Composable (() -> Unit)? = null,
    colors: VisifyButtonColors = VisifyButtonColors.active(),
    buttonHeight: Dp = 56.dp,
    buttonStyle: VisifyButtonStyle = VisifyButtonStyle.CENTER,
    textStyle: TextStyle = VisifyTheme.visifyTypography.button
) {

    val containerColor = if (enabled) colors.containerColor else colors.disabledContainerColor
    val contentColor = if (enabled) colors.contentColor else colors.disabledContentColor

    when (buttonStyle) {
        VisifyButtonStyle.CENTER -> {
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
                        Box(
                            modifier = Modifier
                                .align(Alignment.CenterStart),
                            content = { icon?.invoke() }
                        )
                        Text(
                            text = label,
                            style = textStyle,
                            color = contentColor,
                            textAlign = TextAlign.Center,
                            modifier = Modifier,
                        )
                    }
                }
            }
        }

        VisifyButtonStyle.START -> {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(buttonHeight)
                    .background(containerColor, RoundedCornerShape(6.dp))
                    .clickableNoInteraction(onClick = {
                        if (enabled.not() || isLoading) return@clickableNoInteraction
                        onClick()
                    })
                    .padding(horizontal = 16.dp)
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
                        Text(
                            text = label,
                            style = textStyle,
                            color = contentColor,
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                        )
                        Box(
                            modifier = Modifier
                                .align(Alignment.CenterEnd),
                            content = { icon?.invoke() }
                        )
                    }
                }
            }
        }

    }
}
