package care.visify.ui.kit.components.button

import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.buttonActive

@Composable
fun VisifyTextButton(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = VisifyTheme.visifyTypography.buttonActive,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    indication: Indication? = null,
    onClick: () -> Unit,
) {
    Text(
        text = text,
        style = style,
        modifier = modifier
            .padding(vertical = 19.dp)
            .clickable(
                interactionSource,
                indication,
                onClick = onClick
            )
    )
}