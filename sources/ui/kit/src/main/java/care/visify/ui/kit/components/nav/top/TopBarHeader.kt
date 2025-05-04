package care.visify.ui.kit.components.nav.top

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.headerLargePrimary

@Composable
fun TopBarHeader(
    @StringRes
    headerRes: Int,
) {
    Text(
        text = stringResource(id = headerRes),
        style = VisifyTheme.visifyTypography.headerLargePrimary,
        modifier = Modifier.padding(
            start = VisifyTheme.padding._16dp,
            top = 12.dp,
            bottom = 4.dp
        )
    )
}