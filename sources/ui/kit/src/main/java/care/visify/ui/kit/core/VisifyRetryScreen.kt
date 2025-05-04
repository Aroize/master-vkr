package care.visify.ui.kit.core

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.R
import care.visify.ui.kit.components.button.VisifyButton
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.subheaderPrimary
import care.visify.ui.kit.util.asBgColor

@Composable
fun VisifyRetryScreen(
    message: String,
    onRetryClicked: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .asBgColor()
    ) {
        Text(
            text = message,
            style = VisifyTheme.visifyTypography.subheaderPrimary,
        )
        Spacer(modifier = Modifier.height(16.dp))
        VisifyButton(
            label = stringResource(id = R.string.visify_ui_kit_retry_message),
            onClick = onRetryClicked
        )
    }
}