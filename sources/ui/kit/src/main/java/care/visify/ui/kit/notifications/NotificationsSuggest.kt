package care.visify.ui.kit.notifications

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import care.visify.core.util.NotificationUtils
import care.visify.ui.icons.IconsR
import care.visify.ui.kit.R
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.buttonActive
import care.visify.ui.kit.theme.values.subheaderPrimary
import care.visify.ui.kit.util.clickableNoInteraction

@Composable
fun NotificationsSuggest(
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.settings_notifications_disable_title),
                style = VisifyTheme.visifyTypography.subheaderPrimary,
            )
            Image(
                painter = painterResource(id = IconsR.ic_close_24),
                contentDescription = "close",
                modifier = Modifier.clickableNoInteraction { onClose() }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.settings_notifications_disable_text),
            style = VisifyTheme.visifyTypography.mini
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.settings_notifications_disable_button),
            style = VisifyTheme.visifyTypography.buttonActive,
            modifier = Modifier.clickableNoInteraction {
                val intent = NotificationUtils.createNotificationSettingsIntent(context)
                context.startActivity(intent)
                onClose()
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
    }

}