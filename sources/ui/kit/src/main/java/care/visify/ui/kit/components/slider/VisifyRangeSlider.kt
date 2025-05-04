package care.visify.ui.kit.components.slider

import androidx.annotation.IntRange
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisifyRangeSlider(
    modifier: Modifier = Modifier,
    value: ClosedFloatingPointRange<Float>,
    onValueChange: (ClosedFloatingPointRange<Float>) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    @IntRange(from = 0)
    steps: Int = 0,
    startThumbEnabled : Boolean = true,
    endThumbEnabled : Boolean = true,
    trackEnabled : Boolean = true,
) {
    val startInteractionSource = remember { MutableInteractionSource() }
    val endInteractionSource = remember { MutableInteractionSource() }

    RangeSlider(
        value = value,
        onValueChange = onValueChange,
        valueRange = valueRange,
        modifier = modifier,
        colors = VisifySliderColors.colors(),
        startInteractionSource = startInteractionSource,
        startThumb = {
            SliderDefaults.Thumb(
                interactionSource = startInteractionSource,
                colors = VisifySliderColors.colors(),
                enabled = startThumbEnabled
            )
        },
        endInteractionSource = endInteractionSource,
        endThumb = {
            SliderDefaults.Thumb(
                interactionSource = endInteractionSource,
                colors = VisifySliderColors.colors(),
                enabled = endThumbEnabled
            )
        },
        track = { rangeSliderState ->
            SliderDefaults.Track(
                colors = VisifySliderColors.colors(),
                enabled = trackEnabled,
                rangeSliderState = rangeSliderState
            )
        },
        steps = steps
    )
}