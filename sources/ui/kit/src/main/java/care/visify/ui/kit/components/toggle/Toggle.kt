package care.visify.ui.kit.components.toggle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.mainCellTertiary
import care.visify.ui.kit.util.clickableNoInteraction

@Composable
fun VisifyToggle(
    text: String,
    modifier: Modifier = Modifier,
    onClick: (Boolean) -> Unit,
    enabled: Boolean = false,
    enabledColor: Color = VisifyTheme.colors.frame.white,
    disabledColor: Color = VisifyTheme.colors.label.tertiary
) {
    Box(
        modifier = modifier
            .clickableNoInteraction { onClick(enabled.not()) }
            .clip(RoundedCornerShape(6.dp))
            .clipToBounds()
            .background(color = if (enabled) VisifyTheme.colors.frame.active else VisifyTheme.colors.frame.white)
            .padding(horizontal = 16.dp, vertical = 14.dp)
    ) {
        Text(
            text = text,
            color = if (enabled) enabledColor else disabledColor,
            style = VisifyTheme.visifyTypography.mainCellTertiary
        )
    }
}