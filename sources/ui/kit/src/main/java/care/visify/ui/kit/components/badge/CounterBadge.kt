package care.visify.ui.kit.components.badge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.microPrimary

@Composable
fun CounterBadge(
    count: Int
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.background(
            VisifyTheme.colors.frame.grey,
            shape = RoundedCornerShape(4.dp)
        )
    ) {
        Text(
            text = "$count", style = VisifyTheme.visifyTypography.microPrimary,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}