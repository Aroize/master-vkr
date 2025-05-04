package care.visify.ui.kit.order

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import care.visify.ui.icons.IconsR
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.subheaderPrimary
import care.visify.ui.kit.util.clickableNoInteraction

@Composable
fun VisitHeader(
    name: String,
    isMoreShown: Boolean = true,
    onMoreClicked: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            style = VisifyTheme.visifyTypography.subheaderPrimary,
        )
        if (isMoreShown) {
            Image(
                painter = painterResource(id = IconsR.ic_more_24),
                contentDescription = "More",
                modifier = Modifier.clickableNoInteraction { onMoreClicked() }
            )
        }
    }
}

