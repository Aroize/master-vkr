package care.visify.client.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import care.visify.core.api.sse.repository.RemoteEventDao
import care.visify.core.api.sse.repository.RemoteEventEntity
import care.visify.core.database.converters.LocalDateConverter
import care.visify.feature.catalog.impl.dao.SearchHistoryDao
import care.visify.feature.catalog.impl.dao.SearchHistoryEntity
import care.visify.feature.order.domain.market.dao.PendingOrderDao
import care.visify.feature.order.domain.market.dao.PendingOrderEntity

@Database(
    entities = [
        PendingOrderEntity::class,
        SearchHistoryEntity::class,
        RemoteEventEntity::class
    ],
    version = ClientDatabaseConstants.VERSION,
    autoMigrations = []
)
@TypeConverters(
    LocalDateConverter::class
)
abstract class ClientDatabase : RoomDatabase() {
    abstract fun pendingOrderDao(): PendingOrderDao
    abstract fun searchHistoryDao(): SearchHistoryDao
    abstract fun remoteEventDao(): RemoteEventDao
}