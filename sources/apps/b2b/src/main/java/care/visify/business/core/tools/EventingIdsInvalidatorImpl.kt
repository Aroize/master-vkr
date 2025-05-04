package care.visify.business.core.tools

import care.visify.core.api.sse.repository.RemoteEventRepository
import care.visify.feature.debug.api.EventingIdsInvalidator
import javax.inject.Inject

class EventingIdsInvalidatorImpl @Inject constructor(
    private val remoteEventRepository: RemoteEventRepository
) : EventingIdsInvalidator {
    override suspend fun invalidate() {
        remoteEventRepository.drop()
    }
}