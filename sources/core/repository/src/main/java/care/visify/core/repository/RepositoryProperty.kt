package care.visify.core.repository

import care.visify.api.models.common.CollectionResponse
import care.visify.core.repository.sources.CachedSource
import care.visify.core.repository.sources.CollectionCachedSource
import care.visify.core.repository.sources.RemoteSource

typealias NoParamRepositoryProperty<T> = RepositoryProperty<T, Parameters.NONE>

interface RepositoryProperty<T, Param> where Param : Parameters {
    suspend fun load(
        forceUpdate: Boolean,
        parameters: Param,
    ): T

    suspend fun updateCache(parameters: Param, cached: T)
    suspend fun clearCache()
}

interface RepositoryCollectionProperty<T, Param>
    : RepositoryProperty<CollectionResponse<T>, Param> where Param : Parameters {
    suspend fun updateSingleEntry(updater: (T) -> T)
}

internal class RepositoryPropertyImpl<T, Param>(
    private val remoteSource: RemoteSource<T, Param>,
    private val cachedSource: CachedSource<T, Param>,
) : RepositoryProperty<T, Param> where Param : Parameters {

    override suspend fun load(
        forceUpdate: Boolean,
        parameters: Param,
    ): T {
        if (forceUpdate) {
            return loadInternal(parameters)
        }
        val cached = cachedSource.get(parameters)
        return cached ?: loadInternal(parameters)
    }

    override suspend fun updateCache(parameters: Param, cached: T) {
        cachedSource.put(cached, parameters)
    }

    override suspend fun clearCache() = cachedSource.clear()

    private suspend fun loadInternal(parameters: Param): T {
        val result = remoteSource.load(parameters)
        cachedSource.put(result, parameters)
        return result
    }
}

internal class RepositoryCollectionPropertyImpl<T, Param>(
    private val remoteSource: RemoteSource<CollectionResponse<T>, Param>,
    private val cachedSource: CollectionCachedSource<T, Param>,
) : RepositoryCollectionProperty<T, Param> where Param : Parameters {
    override suspend fun load(forceUpdate: Boolean, parameters: Param): CollectionResponse<T> {
        if (forceUpdate) {
            cachedSource.clear()
            return loadInternal(parameters)
        }
        val cached = cachedSource.get(parameters)
        return cached ?: loadInternal(parameters)
    }

    override suspend fun updateCache(parameters: Param, cached: CollectionResponse<T>) {
        cachedSource.put(cached, parameters)
    }

    override suspend fun updateSingleEntry(updater: (T) -> T) {
        cachedSource.updateSingleEntry(updater)
    }

    override suspend fun clearCache() = cachedSource.clear()

    private suspend fun loadInternal(parameters: Param): CollectionResponse<T> {
        val result = remoteSource.load(parameters)
        cachedSource.put(result, parameters)
        return result
    }
}