package care.visify.ui.kit.modal.footers

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.components.button.VisifyTextButton
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.util.clickableNoInteraction


sealed class VisifyFooterBtn(
    val isVisible: Boolean,
    val color: Color,
    val onClick: () -> Unit,
) {
    class TextButton(
        val text: String,
        color: Color,
        isVisible: Boolean,
        onClick: () -> Unit,
    ): VisifyFooterBtn(isVisible, color, onClick)

    class IconButton(
        @DrawableRes val iconRes: Int,
        isVisible: Boolean,
        color: Color,
        onClick: () -> Unit
    ): VisifyFooterBtn(isVisible, color, onClick)

    class TextAndIconButton(
        val text: String,
        @DrawableRes val iconRes: Int,
        color: Color,
        isVisible: Boolean,
        onClick: () -> Unit
    ): VisifyFooterBtn(isVisible, color, onClick)
}

@Composable
fun PrimaryFooterButton(
    text: String,
    color: Color,
    onClick: () -> Unit
): VisifyFooterBtn = VisifyFooterBtn.TextButton(
    text = text,
    color = color,
    isVisible = true,
    onClick = onClick
)

@Composable
fun SecondaryFooterButton(
    text: String,
    isVisible: Boolean,
    onClick: () -> Unit,
): VisifyFooterBtn = VisifyFooterBtn.TextButton(
    text = text,
    color = VisifyTheme.colors.label.primary,
    isVisible = isVisible,
    onClick = onClick
)

@Composable
fun SecondaryFooterButton(
    @DrawableRes iconRes: Int,
    isVisible: Boolean,
    onClick: () -> Unit,
): VisifyFooterBtn = VisifyFooterBtn.IconButton(
    iconRes = iconRes,
    color = VisifyTheme.colors.label.primary,
    isVisible = isVisible,
    onClick = onClick
)

@Composable
fun SecondaryFooterButton(
    text: String,
    @DrawableRes iconRes: Int,
    isVisible: Boolean,
    onClick: () -> Unit,
): VisifyFooterBtn = VisifyFooterBtn.TextAndIconButton(
    text = text,
    iconRes = iconRes,
    color = VisifyTheme.colors.label.primary,
    isVisible = isVisible,
    onClick = onClick
)

@Composable
fun VisifyDoubleBtnFooter(
    right: VisifyFooterBtn,
    left: VisifyFooterBtn
) {
    VisifyModalFooter(
        style = VisifyFooterContent.Both(
            right = {
                VisifyFooterButton(button = right)
            },
            left = {
                VisifyFooterButton(button = left)
            }
        )
    )
}

@Composable
internal fun VisifyFooterButton(
    button: VisifyFooterBtn
) {
    AnimatedVisibility(visible = button.isVisible) {
        when (button) {
            is VisifyFooterBtn.TextAndIconButton -> Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.clickableNoInteraction(button.onClick)
            ) {
                Image(
                    painter = painterResource(id = button.iconRes),
                    contentDescription = null
                )
                Text(
                    text = button.text,
                    style = VisifyTheme.visifyTypography.button.copy(color = button.color),
                )
            }
            is VisifyFooterBtn.TextButton -> VisifyTextButton(
                text = button.text,
                style = VisifyTheme.visifyTypography.button.copy(color = button.color),
                onClick = button.onClick
            )
            is VisifyFooterBtn.IconButton -> Image(
                painter = painterResource(id = button.iconRes),
                contentDescription = null
            )
        }
    }
}