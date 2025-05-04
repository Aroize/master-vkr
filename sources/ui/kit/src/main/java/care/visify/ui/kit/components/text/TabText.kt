package care.visify.ui.kit.components.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.buttonPrimary
import care.visify.ui.kit.theme.values.buttonTertiary
import care.visify.ui.kit.util.clickableNoInteraction
import care.visify.ui.kit.util.drawTabUnderline

@Composable
fun TabText(
    text: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Text(
        text = text,
        style = when {
            isSelected -> VisifyTheme.visifyTypography.buttonPrimary
            else -> VisifyTheme.visifyTypography.buttonTertiary
        },
        modifier = modifier
            .clickableNoInteraction { onClick() }
            .drawTabUnderline(isSelected)
    )
}