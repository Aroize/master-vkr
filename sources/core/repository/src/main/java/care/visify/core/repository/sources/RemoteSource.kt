package care.visify.core.repository.sources

import care.visify.core.repository.Parameters

fun interface RemoteSource<T, Param> where Param : Parameters {
    suspend fun load(parameters: Param): T
}