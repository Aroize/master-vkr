package care.visify.ui.models.timeslot

import care.visify.core.util.UiText
import java.time.LocalTime

data class TimeSlotUiModel(
    val text: UiText,
    val time: LocalTime,
    val selected: Boolean,
)