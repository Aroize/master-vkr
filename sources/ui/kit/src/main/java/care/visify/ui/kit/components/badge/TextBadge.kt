package care.visify.ui.kit.components.badge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import care.visify.ui.icons.IconsR
import care.visify.ui.kit.theme.VisifyTheme

@Composable
fun TextBadge(
    text: String,
    textColor: Color = VisifyTheme.colors.label.white,
    backgroundColor: Color = VisifyTheme.colors.frame.active,
    hasIcon: Boolean = false,
) {
    Row(
        modifier = Modifier
            .background(backgroundColor, RoundedCornerShape(4.dp))
            .padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(Modifier.width(4.dp))

        Text(
            text = text, style = VisifyTheme.visifyTypography.micro.copy(
                color = textColor
            ),
            modifier = Modifier.padding(vertical = 4.dp)
        )

        Spacer(Modifier.width(4.dp))

        if (hasIcon) {
            Icon(
                painter = painterResource(IconsR.ic_go_12),
                contentDescription = "Go",
                tint = VisifyTheme.colors.label.primary
            )
        }
    }
}