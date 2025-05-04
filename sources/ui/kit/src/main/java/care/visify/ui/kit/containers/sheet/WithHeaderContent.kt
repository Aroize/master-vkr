package care.visify.ui.kit.containers.sheet

import androidx.compose.foundation.layout.Spacer
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
internal fun<T> WithHeaderContent(
    data: List<T>,
    header: String,
    content: @Composable (List<T>) -> Unit
) {
    Text(
        text = header,
        style = VisifyTheme.visifyTypography.subheaderPrimary,
        modifier = Modifier.padding(horizontal = 16.dp)
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = "Можно выбрать несколько",
        style = VisifyTheme.visifyTypography.miniSecondary,
        modifier = Modifier.padding(horizontal = 16.dp)
    )

    Spacer(modifier = Modifier.height(24.dp))

    content(data)

    Spacer(modifier = Modifier.height(24.dp))
}