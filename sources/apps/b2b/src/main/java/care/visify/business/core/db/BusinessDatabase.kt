package care.visify.business.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import care.visify.core.api.sse.repository.RemoteEventDao
import care.visify.core.api.sse.repository.RemoteEventEntity
import care.visify.core.database.converters.LocalDateConverter

@Database(
    entities = [
        RemoteEventEntity::class
    ],
    version = BusinessDatabaseConstants.VERSION,
    autoMigrations = []
)
@TypeConverters(
    LocalDateConverter::class
)
abstract class BusinessDatabase : RoomDatabase() {
    abstract fun remoteEventDao(): RemoteEventDao
}