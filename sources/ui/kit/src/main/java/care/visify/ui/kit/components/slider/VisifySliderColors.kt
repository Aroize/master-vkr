package care.visify.ui.kit.components.slider

import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import care.visify.ui.kit.theme.VisifyTheme

object VisifySliderColors {
    @Composable
    fun colors(
        thumbColor: Color = VisifyTheme.colors.label.active,
        activeTrackColor: Color = VisifyTheme.colors.label.active.copy(alpha = 0.5f),
        inactiveTrackColor: Color = VisifyTheme.colors.frame.disabled,
        activeTickColor: Color = Color.Transparent,
        inactiveTickColor: Color = Color.Transparent,
    ) = SliderDefaults.colors(
        thumbColor,
        activeTrackColor,
        inactiveTrackColor,
        activeTickColor,
        inactiveTickColor
    )
}

