package care.visify.core.repository.sources

import care.visify.api.models.common.CollectionResponse
import care.visify.core.repository.Parameters

interface CollectionCachedSource<T, Param: Parameters> :
    CachedSource<CollectionResponse<T>, Param> {
    suspend fun updateSingleEntry(updater: (T) -> T)
}