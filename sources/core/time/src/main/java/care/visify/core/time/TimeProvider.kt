package care.visify.core.time

import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

interface TimeProvider {
    fun now(): LocalDateTime
    fun today(): LocalDateTime
    fun todayDate(): LocalDate
}

class TimeProviderImpl @Inject constructor(): TimeProvider {

    override fun now(): LocalDateTime = LocalDateTime.now()

    override fun today(): LocalDateTime {
        return now().withMidnight()
    }

    override fun todayDate(): LocalDate = LocalDate.now()
}

fun LocalDateTime.withMidnight(): LocalDateTime {
    return withHour(0)
        .withMinute(0)
        .withSecond(0)
        .withNano(0)
}