package care.visify.ui.kit.components.rating

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import care.visify.ui.icons.IconsR
import care.visify.ui.kit.components.badge.RatingWithStar
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.microWhite

@Composable
@OptIn(ExperimentalLayoutApi::class)
fun VisifyRatingWithChips(
    rating: Float,
    badges: List<String>,
    modifier: Modifier = Modifier,
    ratingTint: Color = VisifyTheme.colors.frame.grey
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(ratingTint, RoundedCornerShape(4.dp))
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

        badges.forEach { badge ->
            Box(
                modifier = Modifier
                    .height(20.dp)
                    .background(VisifyTheme.colors.frame.active, RoundedCornerShape(4.dp))
                    .padding(horizontal = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = badge, style = VisifyTheme.visifyTypography.microWhite)
            }
        }
    }
}