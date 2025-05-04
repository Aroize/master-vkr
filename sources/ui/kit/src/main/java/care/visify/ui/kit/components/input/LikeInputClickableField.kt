package care.visify.ui.kit.components.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.components.divider.VisifyDivider
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.microTertiary
import care.visify.ui.kit.util.clickableNoInteraction

@Composable
fun LikeInputClickableField(
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues = PaddingValues(horizontal = 16.dp),
    hint: String,
    text: String,

    enable: Boolean = true,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .heightIn(min = 64.dp)
            .clickableNoInteraction {
                if (enable) onClick()
            }
            .padding(innerPadding),
        verticalArrangement = Arrangement.Center,
    ) {

        val textColor = if (enable) {
            VisifyTheme.colors.label.primary
        } else {
            VisifyTheme.colors.label.tertiary
        }

        val hintColor = if (enable) {
            VisifyTheme.colors.label.tertiary
        } else {
            VisifyTheme.colors.label.quaternary
        }

        if (text.isNotEmpty()) {
            Text(
                text = hint,
                style = VisifyTheme.visifyTypography.microTertiary,
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(text = text, style = VisifyTheme.visifyTypography.mainCell.copy(
                color = textColor
            ))
        } else {
            Text(text = hint, style = VisifyTheme.visifyTypography.mainCell.copy(
                color = hintColor))
        }

        Spacer(modifier = Modifier.height(6.dp))

        VisifyDivider()
    }
}