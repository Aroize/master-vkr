package care.visify.ui.kit.imageviewer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import care.visify.ui.kit.theme.VisifyTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Suppress("DEPRECATION")
@Composable
fun updateStatusBarWithColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    val originalColor = VisifyTheme.colors.frame.grey
    DisposableEffect(Unit) {
        systemUiController.setStatusBarColor(color = color)
        onDispose {
            systemUiController.setStatusBarColor(color = originalColor)
        }
    }
}