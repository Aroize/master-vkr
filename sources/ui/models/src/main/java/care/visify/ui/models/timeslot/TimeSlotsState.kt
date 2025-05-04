package care.visify.ui.models.timeslot

import care.visify.core.util.UiText
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

data class TimeSlotsState(
    val morningSlots: PersistentList<TimeSlotUiModel>,
    val afternoonSlots: PersistentList<TimeSlotUiModel>,
    val eveningSlots: PersistentList<TimeSlotUiModel>,
) {

    val isAnyAvailable
        get() = morningSlots.isNotEmpty() || afternoonSlots.isNotEmpty() || eveningSlots.isNotEmpty()

    enum class SelectMode {
        SINGLE, MULTI
    }

    fun isAnySelected(): Boolean {
        return morningSlots.any { it.selected } || afternoonSlots.any { it.selected } || eveningSlots.any { it.selected }
    }

    fun select(slot: TimeSlotUiModel, selectMode: SelectMode = SelectMode.SINGLE): TimeSlotsState {
        return when (selectMode) {
            SelectMode.SINGLE -> {
                val newValue = slot.selected.not()
                TimeSlotsState(
                    morningSlots = this.morningSlots.map {
                        if (it == slot) it.copy(selected = newValue) else it.copy(selected = false)
                    }.toPersistentList(),
                    afternoonSlots = this.afternoonSlots.map {
                        if (it == slot) it.copy(selected = newValue) else it.copy(selected = false)
                    }.toPersistentList(),
                    eveningSlots = this.eveningSlots.map {
                        if (it == slot) it.copy(selected = newValue) else it.copy(selected = false)
                    }.toPersistentList(),
                )
            }

            SelectMode.MULTI -> TimeSlotsState(
                morningSlots = this.morningSlots.map { if (it == slot) it.copy(selected = it.selected.not()) else it }
                    .toPersistentList(),
                afternoonSlots = this.afternoonSlots.map { if (it == slot) it.copy(selected = it.selected.not()) else it }
                    .toPersistentList(),
                eveningSlots = this.eveningSlots.map { if (it == slot) it.copy(selected = it.selected.not()) else it }
                    .toPersistentList(),
            )
        }
    }

    companion object {

        val Empty: TimeSlotsState = TimeSlotsState(
            persistentListOf(),
            persistentListOf(),
            persistentListOf()
        )

        fun generate(
            source: LocalDateTime,
            selected: LocalTime? = null,
        ): TimeSlotsState {
            val formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())
            val morningSlots = generateSlots(
                source, formatter, MORNING_START_HOUR, AFTERNOON_START_HOUR, selected
            )
            val afternoonSlots = generateSlots(
                source, formatter, AFTERNOON_START_HOUR, EVENING_START_HOUR, selected
            )
            val eveningSlots = generateSlots(
                source, formatter, EVENING_START_HOUR, END_OF_DAY_HOUR, selected
            )
            return TimeSlotsState(
                morningSlots = morningSlots.toPersistentList(),
                afternoonSlots = afternoonSlots.toPersistentList(),
                eveningSlots = eveningSlots.toPersistentList(),
            )
        }

        private fun generateSlots(
            source: LocalDateTime,
            formatter: DateTimeFormatter,
            from: Int,
            to: Int,
            selected: LocalTime?,
        ): List<TimeSlotUiModel> {
            var date = source
                .withHour(from)
                .withMinute(0)
            val result = mutableListOf<TimeSlotUiModel>()
            while (date.hour < to) {
                date = date.plusMinutes(MINUTES_STEP)
                val time = LocalTime.from(date)
                val slot = date.format(formatter)
                result += TimeSlotUiModel(
                    text = UiText.DynamicString(slot),
                    time = time,
                    selected = time == selected
                )
            }
            return result
        }

        private const val MORNING_START_HOUR = 9
        private const val AFTERNOON_START_HOUR = 12
        private const val EVENING_START_HOUR = 18
        private const val END_OF_DAY_HOUR = 23
        private const val MINUTES_STEP = 30L
    }
}