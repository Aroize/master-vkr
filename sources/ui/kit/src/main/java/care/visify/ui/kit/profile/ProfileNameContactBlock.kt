package care.visify.ui.kit.profile

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.miniSecondary
import care.visify.ui.kit.theme.values.subheaderPrimary

@Composable
fun ProfileNameContactBlock(
    name : String,
    contact: String?
) {

    Spacer(modifier = Modifier.height(24.dp))

    Text(
        text = name,
        style = VisifyTheme.visifyTypography.subheaderPrimary
    )

    contact?.let { data ->

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = data,
            style = VisifyTheme.visifyTypography.miniSecondary
        )
    }

    Spacer(modifier = Modifier.height(40.dp))
}