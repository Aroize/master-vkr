package care.visify.ui.kit.components.indicator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.util.clickableNoInteraction

@Composable
fun VisifyProgressIndicator(
    modifier: Modifier = Modifier,
    color: Color = VisifyTheme.colors.label.active,
    strokeWidth: Dp = ProgressIndicatorDefaults.CircularStrokeWidth,
    trackColor: Color = Color.Transparent
) {
    CircularProgressIndicator(
        modifier = modifier,
        color = color,
        strokeWidth = strokeWidth,
        trackColor = trackColor,
        strokeCap = StrokeCap.Round
    )
}

@Composable
fun VisifyProgressIndicatorDialog(
    isShowing: Boolean = false,
) {
    if (isShowing)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(VisifyTheme.colors.frame.dialogOverlay)
                .clickableNoInteraction {  },
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(VisifyTheme.colors.label.white, VisifyTheme.cornerShape.rounded6dp),
                contentAlignment = Alignment.Center
            ) {
                VisifyProgressIndicator(trackColor = VisifyTheme.colors.label.quaternary)
            }
        }
}