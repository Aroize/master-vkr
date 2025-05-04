package care.visify.ui.kit.order

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.R
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.buttonPrimary
import care.visify.ui.kit.util.clickableNoInteraction

@Composable
fun VisitDetails(
    onDetailsClicked: () -> Unit,
) {
    Text(
        text = stringResource(id = R.string.visits_details),
        modifier = Modifier
            .padding(vertical = 19.dp)
            .clickableNoInteraction {
                onDetailsClicked()
            },
        style = VisifyTheme.visifyTypography.buttonPrimary
    )
}