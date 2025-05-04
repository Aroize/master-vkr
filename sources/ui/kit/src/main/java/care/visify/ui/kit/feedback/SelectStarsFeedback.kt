package care.visify.ui.kit.feedback

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import care.visify.ui.icons.IconsR
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.util.clickableNoInteraction


@Composable
fun SelectStarsFeedback(
    rating: Int,
    onValueChanged: (Int) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            repeat(5) {
                Icon(
                    painter = painterResource(id = IconsR.ic_star_16),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                        .clickableNoInteraction {
                            if (enabled) onValueChanged(it + 1)
                        },
                    tint = if (it < rating)
                        VisifyTheme.colors.frame.yellow
                    else
                        VisifyTheme.colors.frame.disabled
                )
            }
        }
    }
}