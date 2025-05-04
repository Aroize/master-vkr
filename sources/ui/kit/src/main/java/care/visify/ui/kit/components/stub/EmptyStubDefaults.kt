package care.visify.ui.kit.components.stub

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.theme.VisifyTheme

object EmptyStubDefaults {

    const val fullScreenFraction = 0.85f

    private val smallRadius = 32.dp
    private val mediumRadius = 52.dp
    private val largeRadius = 72.dp

    @Composable
    fun Modifier.drawCircles(

    ): Modifier {
        val circleColor = VisifyTheme.colors.label.tertiary
        return this.drawBehind {
            drawCircle(
                circleColor,
                radius = smallRadius.toPx(),
                alpha = 0.8f
            )
            drawCircle(
                circleColor,
                radius = mediumRadius.toPx(),
                alpha = 0.4f
            )
            drawCircle(
                circleColor,
                radius = largeRadius.toPx(),
                alpha = 0.1f
            )
        }
    }
}