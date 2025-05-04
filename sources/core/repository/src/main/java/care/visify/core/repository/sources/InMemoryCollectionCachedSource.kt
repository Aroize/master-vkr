package care.visify.core.repository.sources

import care.visify.api.models.common.CollectionResponse
import care.visify.core.repository.Parameters
import java.util.concurrent.ConcurrentHashMap

class InMemoryCollectionCachedSource<T, Param: Parameters>: CollectionCachedSource<T, Param> {

    private val cache = ConcurrentHashMap<Param, CollectionResponse<T>>()

    override suspend fun get(param: Param): CollectionResponse<T>? = cache[param]

    override suspend fun put(value: CollectionResponse<T>, param: Param) {
        cache[param] = value
    }

    override suspend fun clear() = cache.clear()

    override suspend fun updateSingleEntry(updater: (T) -> T) {
        cache.keys
            .forEach { key ->
                val cached = cache[key] as CollectionResponse<T>
                val updated = cached.items.map(updater)
                cache[key] = cached.copy(items = updated)
            }
    }
}