package care.visify.ui.models.calendar

import care.visify.core.util.UiText
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.PersistentSet
import kotlinx.collections.immutable.minus
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentSetOf
import kotlinx.collections.immutable.toPersistentList
import java.time.LocalDate

data class SelectDateState(
    val months: PersistentList<CalendarMonthUiModel>,
    val desc: UiText? = null,
) {

    fun selectWithoutDescChanging(
        changedMonth: CalendarMonthUiModel,
        selectedDates: PersistentSet<LocalDate>,
        //TODO MULTIPLE SELECT
        mode: SelectMode = SelectMode.SINGLE,
    ): SelectDateState {
        return SelectDateState(
            months.select(changedMonth, selectedDates), null
        )
    }

    fun selectWithDescChanging(
        changedMonth: CalendarMonthUiModel,
        selectedDates: PersistentSet<LocalDate>,
        //TODO MULTIPLE SELECT
        mode: SelectMode = SelectMode.SINGLE,
    ): SelectDateState {
        val diff = selectedDates - changedMonth.selectedDays
        val newDesc = if (diff.isEmpty()) null else this.desc
        val newMonths = months.select(changedMonth, selectedDates)

        return SelectDateState(newMonths, newDesc)
    }

    companion object {
        //Если больше 3, пишем что слотов много
        const val MANY_SLOTS_COUNT = 3

        val Empty = SelectDateState(months = persistentListOf(), desc = null)

        fun generateStateFromToday(
            today: LocalDate,
        ): SelectDateState = SelectDateState(generateMonthsFromToday(today))

        fun generateMonthsFromToday(
            today: LocalDate,
        ): PersistentList<CalendarMonthUiModel> {
            val exceptDays = IntArray(today.dayOfMonth) { it }
                .drop(1)
            val actualMonth = CalendarMonthUiModel.fromLocalDate(
                date = today,
                mode = SelectMode.SINGLE,
                strategy = AvailabilityStrategy.AnyExcept(exceptDays = exceptDays.toSet())
            )
            val states = listOf(
                actualMonth,
                CalendarMonthUiModel.fromLocalDate(
                    today.plusMonths(1)
                ),
                CalendarMonthUiModel.fromLocalDate(
                    today.plusMonths(2)
                )
            ).toPersistentList()
            return states
        }
    }
}

fun PersistentList<CalendarMonthUiModel>.select(
    changedMonth: CalendarMonthUiModel,
    selectedDates: PersistentSet<LocalDate>,
    //TODO MULTIPLE SELECT
    mode: SelectMode = SelectMode.SINGLE,
): PersistentList<CalendarMonthUiModel> {
    return this.map {
        if (it.month != changedMonth.month) {
            it.copy(
                selectedDays = persistentSetOf()
            )
        } else {
            it.copy(
                selectedDays = selectedDates
            )
        }
    }.toPersistentList()
}