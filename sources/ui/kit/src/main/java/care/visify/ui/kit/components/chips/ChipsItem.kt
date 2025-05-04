package care.visify.ui.kit.components.chips

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.R
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.miniPrimary
import care.visify.ui.kit.theme.values.miniWhite
import care.visify.ui.kit.util.clickableNoInteraction

@Composable
fun ChipsItem(
    text: String,
    state: ChipsState,
    onClick: () -> Unit,
    onRemove: () -> Unit,
) {
    val containerColor = when (state.isActive) {
        true -> VisifyTheme.colors.frame.active
        else -> VisifyTheme.colors.frame.white
    }

    val textStyle = when (state.isActive) {
        true -> VisifyTheme.visifyTypography.miniWhite
        else -> VisifyTheme.visifyTypography.miniPrimary
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(28.dp)
            .background(
                containerColor,
                RoundedCornerShape(20.dp)
            )
            .padding(start = 10.dp, end = 8.dp)
    ) {
        Text(
            text = text,
            style = textStyle,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .clickableNoInteraction { onClick() }
        )

        if (state == ChipsState.ActiveWithRemove) {
            Spacer(modifier = Modifier.width(2.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_close_chips_15),
                contentDescription = null,
                modifier = Modifier
                    .padding(bottom = 2.dp)
                    .clickableNoInteraction { onRemove() }
            )
        }
    }
}