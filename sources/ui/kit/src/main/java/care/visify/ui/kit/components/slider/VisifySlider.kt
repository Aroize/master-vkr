package care.visify.ui.kit.components.slider

import androidx.annotation.IntRange
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisifySlider(
    modifier: Modifier = Modifier,
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    @IntRange(from = 0)
    steps: Int = 0,
    onValueChangeFinished: (() -> Unit)? = null,
    thumbEnabled : Boolean = true,
    trackEnabled : Boolean = true,
) {
    val thumbInteractionSource = remember { MutableInteractionSource() }

    Slider(
        value = value,
        onValueChange = onValueChange,
        colors = VisifySliderColors.colors(),
        steps = steps,
        onValueChangeFinished = onValueChangeFinished,
        modifier = modifier,
        thumb = {
            SliderDefaults.Thumb(
                interactionSource = thumbInteractionSource,
                colors = VisifySliderColors.colors(),
                enabled = thumbEnabled
            )
        },
        track = { sliderState ->
            SliderDefaults.Track(
                colors = VisifySliderColors.colors(),
                enabled = trackEnabled,
                sliderState = sliderState
            )
        },
        valueRange = valueRange
    )
}
