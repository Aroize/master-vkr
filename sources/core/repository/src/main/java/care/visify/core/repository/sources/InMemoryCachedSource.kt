package care.visify.core.repository.sources

import care.visify.core.repository.Parameters
import java.util.concurrent.ConcurrentHashMap

class InMemoryCachedSource<T, Params> : CachedSource<T, Params> where Params : Parameters {

    private val cache: ConcurrentHashMap<Params, T> = ConcurrentHashMap()

    override suspend fun get(param: Params): T? {
        return cache[param]
    }

    override suspend fun put(value: T, param: Params) {
        cache[param] = value
    }

    override suspend fun clear() = cache.clear()
}