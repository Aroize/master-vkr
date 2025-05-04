package care.visify.ui.kit.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.components.image.VisifyImageById
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.buttonPrimary
import care.visify.ui.kit.util.clickableNoInteraction
import care.visify.ui.models.category.CategoryUiModel

@Composable
fun CategoryBadgeItem(
    category: CategoryUiModel,
    onClick: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .background(
                VisifyTheme.colors.frame.white,
                RoundedCornerShape(6.dp)
            )
            .padding(start = 14.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
            .clickableNoInteraction { onClick(category.id) }
    ) {
        VisifyImageById(
            model = category.icon,
            requestBuilderTransform = { it },
            modifier = Modifier.size(26.dp),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = category.text,
            style = VisifyTheme.visifyTypography.buttonPrimary
        )
    }
}