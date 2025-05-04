package care.visify.core.repository.sources

import care.visify.core.repository.Parameters

class NoParamCachedSource<T> : CachedSource<T, Parameters.NONE> {

    @Volatile
    private var cached: T? = null

    override suspend fun get(param: Parameters.NONE): T? = cached

    override suspend fun put(value: T, param: Parameters.NONE) = synchronized(this) {
        cached = value
    }

    override suspend fun clear() = Unit
}