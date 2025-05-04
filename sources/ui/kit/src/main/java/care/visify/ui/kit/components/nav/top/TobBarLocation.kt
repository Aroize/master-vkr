package care.visify.ui.kit.components.nav.top

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import care.visify.core.util.multiLet
import care.visify.ui.icons.IconsR
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.buttonActive
import care.visify.ui.kit.util.clickableNoInteraction

@Composable
fun TobBarLocation(
    city: String,
    onCityClick: () -> Unit = {},
    @StringRes
    actionTextRes: Int? = null,
    onActionClick: (() -> Unit)? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.clickableNoInteraction(onCityClick),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = IconsR.ic_location_24),
                contentDescription = "City",
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = city,
                style = VisifyTheme.visifyTypography.button.copy(
                    color = VisifyTheme.colors.frame.black
                )
            )
        }

        multiLet(actionTextRes, onActionClick) { res, onClick ->
            Text(
                text = stringResource(id = res),
                style = VisifyTheme.visifyTypography.buttonActive,
                modifier = Modifier.clickableNoInteraction(onClick)
            )
        }
    }
}