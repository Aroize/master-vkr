package care.visify.core.database.converters

import androidx.room.TypeConverter
import care.visify.core.util.DateUtils
import java.time.LocalDateTime

class LocalDateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDateTime? {
        return value?.let { DateUtils.convertToLocalDate(it) }
    }
    @TypeConverter
    fun toTimestamp(value: LocalDateTime?): Long? {
        return value?.let { DateUtils.convertToTimestamp(it) }
    }
}