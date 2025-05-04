package care.visify.ui.kit.components.text

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import care.visify.ui.icons.IconsR
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.subheaderPrimary

@Composable
fun CheckmarkedTitle(
    name: String,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        Text(
            text = name,
            style = VisifyTheme.visifyTypography.subheaderPrimary,
        )
        Image(
            painter = painterResource(id = IconsR.ic_verified_8),
            contentDescription = "Verified",
            modifier = Modifier.padding(start = 2.dp)
        )
    }
}