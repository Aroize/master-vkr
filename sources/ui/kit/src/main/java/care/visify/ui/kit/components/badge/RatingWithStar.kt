package care.visify.ui.kit.components.badge

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.R
import care.visify.ui.kit.theme.VisifyTheme

@Composable
fun RatingWithStar(
    rating: Float,
    modifier: Modifier = Modifier,
    isSmall: Boolean = false,
    textColor: Color = VisifyTheme.colors.label.primary,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_star_16),
            contentDescription = null,
            modifier = Modifier
                .padding(bottom = 1.dp)
                .then(if (isSmall) Modifier.size(11.dp) else Modifier)
        )
        Text(
            text = "%.1f".format(rating).replace('.', ','),
            style = when {
                isSmall -> VisifyTheme.visifyTypography.micro
                else -> VisifyTheme.visifyTypography.mini
            },
            color = textColor,
        )
    }
}