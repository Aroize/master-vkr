package care.visify.core.util

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

object DateUtils {
    fun convertToTimestamp(time: LocalDateTime?): Long {
        if (time == null) return -1
        return time.atOffset(ZoneOffset.UTC)
            .toInstant()
            .toEpochMilli()
    }

    fun convertToLocalDate(time: Long): LocalDateTime {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneOffset.UTC)
    }


    fun fromUnix(ts: Long): LocalDateTime {
        val zoneId = ZoneId.systemDefault()
        val instant = Instant.ofEpochSecond(ts)
        return LocalDateTime.ofInstant(instant, zoneId)
    }
}