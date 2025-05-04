package care.visify.ui.kit.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import care.visify.ui.kit.theme.values.Colors
import care.visify.ui.kit.theme.values.CornerShape
import care.visify.ui.kit.theme.values.Padding
import care.visify.ui.kit.theme.values.baseCornerShape
import care.visify.ui.kit.theme.values.baseDarkPalette
import care.visify.ui.kit.theme.values.baseLightPalette
import care.visify.ui.kit.theme.values.basePadding
import care.visify.ui.kit.theme.values.visifyTypography


@Composable
fun VisifyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    providedValues: Array<ProvidedValue<*>> = emptyArray(),
    content: @Composable () -> Unit,
) {
    val colors: Colors = when {
        darkTheme -> baseDarkPalette
        else -> baseLightPalette
    }

    val visifyTypography = visifyTypography

    val padding: Padding = basePadding

    val cornerShape: CornerShape = baseCornerShape

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as? Activity)?.window?.let { window ->
                window.statusBarColor = colors.frame.grey.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                    !darkTheme
            }
        }
    }

    CompositionLocalProvider(
        *providedValues,
        LocalCustomColors provides colors,
        LocalCustomVisifyTypography provides visifyTypography,
        LocalCustomPadding provides padding,
        LocalCustomCornerShape provides cornerShape,
        content = content
    )
}