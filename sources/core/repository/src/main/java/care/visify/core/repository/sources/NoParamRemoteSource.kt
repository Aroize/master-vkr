package care.visify.core.repository.sources

import care.visify.core.repository.Parameters

class NoParamRemoteSource<T>(
    private val delegate: suspend () -> T
) : RemoteSource<T, Parameters.NONE> {
    override suspend fun load(parameters: Parameters.NONE): T = delegate()
}