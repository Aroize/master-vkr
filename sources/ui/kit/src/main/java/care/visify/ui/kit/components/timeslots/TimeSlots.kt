package care.visify.ui.kit.components.timeslots

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.components.toggle.VisifyToggle
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.miniSecondary
import care.visify.ui.kit.util.asString
import care.visify.ui.models.timeslot.TimeSlotUiModel
import kotlinx.collections.immutable.PersistentList

@Composable
fun TimeSlotRowList(
    title: String,
    timeSlotsList: PersistentList<TimeSlotUiModel>,
    onClick: (TimeSlotUiModel) -> Unit,
) {
    Text(
        text = title,
        style = VisifyTheme.visifyTypography.miniSecondary,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp),
    )
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        items(items = timeSlotsList) {
            TimeSlotItem(slot = it, onClick)
        }
    }
}

@Composable
private fun TimeSlotItem(
    slot: TimeSlotUiModel,
    onClick: (TimeSlotUiModel) -> Unit,
) {
    VisifyToggle(
        text = slot.text.asString(),
        onClick = { onClick(slot) },
        enabled = slot.selected,
        disabledColor = VisifyTheme.colors.label.primary
    )
}