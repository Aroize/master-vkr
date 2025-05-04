package care.visify.ui.kit.order

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.miniSecondary


@Composable
fun VisitDate(
    date: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = date,
        style = VisifyTheme.visifyTypography.miniSecondary,
        modifier = modifier
    )
}