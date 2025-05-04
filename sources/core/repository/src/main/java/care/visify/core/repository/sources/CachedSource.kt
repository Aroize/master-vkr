package care.visify.core.repository.sources

import care.visify.core.repository.Parameters

interface CachedSource<T, Param> where Param : Parameters {
    suspend fun get(param: Param): T?
    suspend fun put(value: T, param: Param)
    suspend fun clear()
}