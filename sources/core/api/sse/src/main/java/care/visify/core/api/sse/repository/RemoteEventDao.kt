package care.visify.core.api.sse.repository

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import java.time.LocalDateTime

@Entity(tableName = "remote_event")
data class RemoteEventEntity(
    @ColumnInfo(name = "remote_id") val remoteId: Long = 0,
    @ColumnInfo(name = "created_ts") val createdTimestamp: LocalDateTime = LocalDateTime.now()
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}

@Dao
abstract class RemoteEventDao {

    @Query("SELECT EXISTS (SELECT 1 FROM remote_event WHERE remote_id = :remoteId LIMIT 1)")
    abstract fun existsWithId(remoteId: Long): Boolean

    @Insert
    abstract fun insert(entity: RemoteEventEntity)

    @Query("DELETE FROM remote_event")
    abstract fun deleteAll()
}