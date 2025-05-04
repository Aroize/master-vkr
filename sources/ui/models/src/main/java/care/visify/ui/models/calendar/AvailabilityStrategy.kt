package care.visify.ui.models.calendar

sealed interface AvailabilityStrategy {

    fun isDayAvailable(day: Int): Boolean

    data object All : AvailabilityStrategy {
        override fun isDayAvailable(day: Int): Boolean = true
    }

    class AnyExcept(private val exceptDays: Set<Int>): AvailabilityStrategy {
        override fun isDayAvailable(day: Int): Boolean = day !in exceptDays
    }

    class Only(private val onlyDays: Set<Int>): AvailabilityStrategy {
        override fun isDayAvailable(day: Int): Boolean = day in onlyDays
    }
}