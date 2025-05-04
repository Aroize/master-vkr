package care.visify.ui.models.calendar

import kotlinx.collections.immutable.PersistentSet
import kotlinx.collections.immutable.persistentSetOf
import java.time.LocalDate
import java.time.Month
import java.time.YearMonth
import javax.annotation.concurrent.Immutable

@Immutable
data class CalendarMonthUiModel(
    val date: LocalDate,
    val month: Month,
    val daysInMonth: Int,
    val firstDayOfMonth: Int,
    val selectedDays: PersistentSet<LocalDate>,
    val strategy: AvailabilityStrategy,
    val mode: SelectMode,
) {
    companion object {

        fun default() = fromLocalDate(LocalDate.now())

        fun fromLocalDate(
            date: LocalDate,
            mode: SelectMode = SelectMode.SINGLE,
            strategy: AvailabilityStrategy = AvailabilityStrategy.All,
            selectedDays: PersistentSet<LocalDate> = persistentSetOf()
        ): CalendarMonthUiModel {
            return with(date.withDayOfMonth(1)) {
                val daysInMonth = month.length(YearMonth.from(date).isLeapYear)
                val dayOfWeek = dayOfWeek.ordinal
                CalendarMonthUiModel(
                    date = this,
                    month = date.month,
                    daysInMonth = daysInMonth,
                    firstDayOfMonth = dayOfWeek,
                    selectedDays = selectedDays,
                    strategy = strategy,
                    mode = mode
                )
            }
        }
    }
}