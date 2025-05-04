package care.visify.core.repository.sources

import care.visify.core.repository.Parameters

class NoOpCachedSource<T, Param: Parameters>: CachedSource<T, Param> {
    override suspend fun get(param: Param): T? = null
    override suspend fun put(value: T, param: Param) = Unit
    override suspend fun clear() = Unit
}