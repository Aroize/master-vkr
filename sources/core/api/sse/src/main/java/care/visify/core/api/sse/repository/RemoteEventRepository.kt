package care.visify.core.api.sse.repository

import care.visify.core.time.TimeProvider
import javax.inject.Inject

interface RemoteEventRepository {
    fun checkExists(id: Long): Boolean
    fun create(id: Long)

    fun drop()
}

class RemoteEventRepositoryImpl @Inject constructor(
    private val remoteEventDao: RemoteEventDao,
    private val timeProvider: TimeProvider
): RemoteEventRepository {
    override fun checkExists(id: Long): Boolean = remoteEventDao.existsWithId(id)

    override fun create(id: Long) {
        remoteEventDao.insert(
            RemoteEventEntity(
                remoteId = id,
                createdTimestamp = timeProvider.now()
            )
        )
    }

    override fun drop() {
        remoteEventDao.deleteAll()
    }
}