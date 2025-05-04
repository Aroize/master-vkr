package care.visify.ui.kit.modal.headers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.miniSecondary
import care.visify.ui.kit.theme.values.subheaderPrimary

@Composable
fun VisifyModalHeader(
    titleText: String,
    subtitleText: String? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = VisifyTheme.padding._16dp)
    ) {
        Text(
            text = titleText,
            style = VisifyTheme.visifyTypography.subheaderPrimary
        )

        subtitleText?.let { text ->
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = text,
                style = VisifyTheme.visifyTypography.miniSecondary
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}