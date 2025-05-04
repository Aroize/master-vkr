package care.visify.ui.kit.time

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.theme.VisifyTheme
import java.time.LocalTime

@Composable
fun VisifyTimePicker(
    selectedTime: LocalTime,
    onValueChange: (LocalTime) -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(centerSelectorHeight.dp)
                .background(
                    color = VisifyTheme.colors.frame.grey,
                    shape = RoundedCornerShape(8.dp),
                )
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TimeColumnPicker(
                selectedValue = selectedTime.hour,
                onValueChange = { onValueChange(selectedTime.withHour(it)) },
                range = hoursRange,
            )

            Text(
                text = ":", style = VisifyTheme.visifyTypography.subHeader,
                modifier = Modifier
                    .width(spacerWidth.dp),
                textAlign = TextAlign.Center
            )

            TimeColumnPicker(
                selectedValue = selectedTime.minute,
                onValueChange = { onValueChange(selectedTime.withMinute(it)) },
                range = minutesRange,
            )
        }
    }
}
