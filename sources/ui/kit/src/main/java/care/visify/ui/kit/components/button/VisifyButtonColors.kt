package care.visify.ui.kit.components.button

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import care.visify.ui.kit.theme.VisifyTheme


@Immutable
data class VisifyButtonColors(
    val containerColor: Color,
    val contentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color,
) {
    companion object {
        @Composable
        fun active() = VisifyButtonColors(
            containerColor = VisifyTheme.colors.frame.active,
            contentColor = VisifyTheme.colors.frame.white,
            disabledContainerColor = VisifyTheme.colors.frame.disabled,
            disabledContentColor = VisifyTheme.colors.label.tertiary,
        )

        @Composable
        fun white() = VisifyButtonColors(
            containerColor = VisifyTheme.colors.label.white,
            contentColor = VisifyTheme.colors.label.primary,
            disabledContainerColor = VisifyTheme.colors.frame.disabled,
            disabledContentColor = VisifyTheme.colors.label.tertiary,
        )

        @Composable
        fun whiteActive() = VisifyButtonColors(
            containerColor = VisifyTheme.colors.label.white,
            contentColor = VisifyTheme.colors.label.active,
            disabledContainerColor = VisifyTheme.colors.frame.disabled,
            disabledContentColor = VisifyTheme.colors.label.tertiary,
        )

        @Composable
        fun grey() = VisifyButtonColors(
            containerColor = VisifyTheme.colors.frame.grey,
            contentColor = VisifyTheme.colors.label.primary,
            disabledContainerColor = VisifyTheme.colors.frame.disabled,
            disabledContentColor = VisifyTheme.colors.label.tertiary,
        )
    }
}
