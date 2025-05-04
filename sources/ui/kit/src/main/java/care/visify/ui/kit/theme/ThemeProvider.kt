package care.visify.ui.kit.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import care.visify.ui.kit.theme.values.Colors
import care.visify.ui.kit.theme.values.CornerShape
import care.visify.ui.kit.theme.values.Padding
import care.visify.ui.kit.theme.values.VisifyTypography

// Use this class to provide colors, fonts, paddings, and etc...
object VisifyTheme {

    val colors: Colors
        @Composable
        get() = LocalCustomColors.current

    val visifyTypography: VisifyTypography
        @Composable
        get() = LocalCustomVisifyTypography.current

    val padding: Padding
        @Composable
        get() = LocalCustomPadding.current

    val cornerShape: CornerShape
        @Composable
        get() = LocalCustomCornerShape.current
}

val LocalCustomColors = staticCompositionLocalOf<Colors> {
    error("No colors provided")
}

val LocalCustomVisifyTypography =
    staticCompositionLocalOf<VisifyTypography> {
        error("No font provided")
    }

val LocalCustomPadding = staticCompositionLocalOf<Padding> {
    error("No padding provided")
}

val LocalCustomCornerShape = staticCompositionLocalOf<CornerShape> {
    error("No corner shape provided")
}