package care.visify.ui.kit.util.formatter

import android.content.Context
import care.visify.ui.kit.R
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object TimeFormatter {

    @ApplicationContext
    private lateinit var ctx: Context

    private val slotFormatter by lazy { DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault()) }

    fun init(ctx: Context) {
        TimeFormatter.ctx = ctx
    }

    @JvmStatic
    fun formatDuration(durationInMinutes: Int): String {
        val resources = ctx.resources
        val minutes = durationInMinutes % 60
        val hours = durationInMinutes / 60

        val minutesFormatted = when {
            minutes > 0 -> resources.getQuantityString(R.plurals.timings_minutes, minutes, minutes)
            else -> ""
        }
        val hoursFormatted = when {
            hours > 0 -> resources.getQuantityString(R.plurals.timings_hours, hours, hours)
            else -> ""
        }
        return "$hoursFormatted $minutesFormatted".trim()
    }

    @JvmStatic
    fun formatSlot(slot: LocalDateTime): String {
        return slotFormatter.format(slot)
    }

    @JvmStatic
    fun formatSlot(slot: LocalTime): String {
        return slotFormatter.format(slot)
    }

    @JvmStatic
    fun formatSlotsList(slots: List<LocalDateTime>): String {
        return slots.joinToString { formatSlot(it) }
    }

    @JvmStatic
    fun formatDateFull(date: LocalDate): String {
        return formatDateViaPattern(date) { day, month, week -> "$day $month, $week" }
    }

    @JvmStatic
    fun formatDateShort(date: LocalDateTime): String {
        return formatDateViaPattern(date.toLocalDate()) { day, month, _ -> "$day $month" }
    }

    @JvmStatic
    fun formatDateShort(date: LocalDate): String {
        return formatDateViaPattern(date) { day, month, _ -> "$day $month" }
    }

    @JvmStatic
    fun formatDateFull(date: LocalDateTime): String {
        return formatDateViaPattern(date.toLocalDate()) { day, month, week -> "$day $month, $week" }
    }

    @JvmStatic
    fun formatFullWithSlot(date: LocalDateTime): String {
        return "${formatDateFull(date.toLocalDate())} в ${formatSlot(date)}"
    }

    @JvmStatic
    fun formatPartialWithSlot(date: LocalDateTime): String {
        val time = formatDateViaPattern(date.toLocalDate()) { day, month, _ -> "$day $month" }
        return "$time в ${formatSlot(date)}"
    }

    /**
     * pattern (day, month, week) -> date-str
     */
    @JvmStatic
    fun formatDateViaPattern(
        date: LocalDate, pattern: (Int, String, String) -> String,
    ): String {
        val resources = ctx.resources
        val month = resources.getStringArray(R.array.month_array_order)[date.month.value - 1]
        val dayOfWeek = resources.getStringArray(R.array.days_of_week)[date.dayOfWeek.value - 1]
        return pattern(date.dayOfMonth, month, dayOfWeek)
    }

    @JvmStatic
    fun formatDateTimeSmall(date: LocalDateTime): String {
        val now = LocalDateTime.now()
        val formatterDateTime = DateTimeFormatter.ofPattern("d MMMM в HH:mm")

        return when {
            date.toLocalDate().isEqual(now.toLocalDate()) -> {
                formatSlot(date)
            }

            date.toLocalDate().isEqual(now.minusDays(1).toLocalDate()) -> {
                "Вчера в " + formatSlot(date)
            }

            else -> {
                date.format(formatterDateTime)
            }
        }
    }

    @JvmStatic
    fun formatShortWeekday(
        date: LocalDateTime,
    ): String {
        val resources = ctx.resources
        return resources.getStringArray(R.array.days_of_week_short)[date.dayOfWeek.value - 1]
    }

    @JvmStatic
    fun formatSlotWithDuration(
        slot: LocalTime,
        durationInMinutes: Int
    ): String {
        return "${formatSlot(slot)} (~${formatDuration(durationInMinutes)})"
    }

    @JvmStatic
    fun formatRange(
        from: LocalTime,
        to: LocalTime,
    ): String { return "${formatSlot(from)}—${formatSlot(to)}" }
}