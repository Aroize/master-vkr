package care.visify.ui.kit.components.badge

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import care.visify.ui.icons.IconsR
import care.visify.ui.kit.theme.VisifyTheme

@Composable
fun RatingBadge(
    rating: Float,
    backgroundColor : Color = VisifyTheme.colors.frame.grey
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(
                backgroundColor,
                RoundedCornerShape(4.dp)
            )
            .size(54.dp, 20.dp)
    ) {
        RatingWithStar(rating = rating)
        Image(
            painter = painterResource(id = IconsR.ic_go_12),
            modifier = Modifier
                .padding(bottom = 1.dp)
                .size(12.dp),
            contentDescription = null
        )
    }
}