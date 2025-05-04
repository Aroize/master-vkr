package care.visify.core.repository

import care.visify.api.models.common.BatchResponse
import care.visify.api.models.common.CollectionResponse
import care.visify.core.repository.sources.CachedSource
import care.visify.core.repository.sources.CollectionCachedSource
import care.visify.core.repository.sources.InMemoryCachedSource
import care.visify.core.repository.sources.InMemoryCollectionCachedSource
import care.visify.core.repository.sources.NoOpCachedSource
import care.visify.core.repository.sources.NoParamCachedSource
import care.visify.core.repository.sources.NoParamRemoteSource
import care.visify.core.repository.sources.RemoteSource

inline fun<R, Params: Parameters> wrapSafeCall(
    crossinline call: suspend (Params) -> R
) = RemoteCallHelper<R, Params>(delegate = { call(it) })


fun<T, Param : Parameters> noCacheRepositoryProperty(
    remoteSource: RemoteSource<T, Param>
): RepositoryProperty<T, Param> = repositoryProperty(
    remoteSource = remoteSource,
    cachedSource = NoOpCachedSource()
)

fun<T> noCacheRepositoryPropertyWithResponse(
    remoteSource: suspend  () -> T
): NoParamRepositoryProperty<T> = repositoryProperty(
    remoteSource = NoParamRemoteSource(remoteSource),
    cachedSource = NoOpCachedSource()
)

fun<Param : Parameters> noCacheRepositoryPropertyWithoutResponse(
    remoteSource: RemoteSource<Unit, Param>
): RepositoryProperty<Unit, Param> = repositoryProperty(
    remoteSource = remoteSource,
    cachedSource = NoOpCachedSource()
)

fun<T> noParamInMemoryRepositoryProperty(
    remoteSource: suspend () -> T
): RepositoryProperty<T, Parameters.NONE> = repositoryProperty(
    remoteSource = NoParamRemoteSource(remoteSource),
    cachedSource = NoParamCachedSource()
)

fun<T, Param : Parameters> inMemoryRepositoryProperty(
    remoteSource: RemoteSource<T, Param>
): RepositoryProperty<T, Param> = repositoryProperty(
    remoteSource = remoteSource,
    cachedSource = InMemoryCachedSource()
)

fun<T, Param : Parameters> inMemoryCollectionProperty(
    remoteSource: RemoteSource<CollectionResponse<T>, Param>
): RepositoryCollectionProperty<T, Param> = collectionRepositoryProperty(
    remoteSource = remoteSource,
    cachedSource = InMemoryCollectionCachedSource()
)

fun<T, Param : Parameters> repositoryProperty(
    remoteSource: RemoteSource<T, Param>,
    cachedSource: CachedSource<T, Param>
): RepositoryProperty<T, Param> = RepositoryPropertyImpl(
    remoteSource = RemoteCallHelper(remoteSource),
    cachedSource = cachedSource,
)

fun<T, Param : Parameters> collectionRepositoryProperty(
    remoteSource: RemoteSource<CollectionResponse<T>, Param>,
    cachedSource: CollectionCachedSource<T, Param>
): RepositoryCollectionProperty<T, Param> = RepositoryCollectionPropertyImpl(
    remoteSource = RemoteCallHelper(remoteSource),
    cachedSource = cachedSource
)

fun<T, Param : Parameters> inMemoryBatchProperty(
    remoteSource: RemoteSource<BatchResponse<T>, Param>
): RepositoryProperty<BatchResponse<T>, Param> = repositoryProperty(
    remoteSource = remoteSource,
    cachedSource = InMemoryCachedSource()
)

suspend fun<T> RepositoryProperty<T, Parameters.NONE>.load(forceUpdate: Boolean)
    = load(forceUpdate, Parameters)

suspend fun<T, Param: Parameters> RepositoryProperty<T, Param>.execute(parameters: Param): T
    = load(forceUpdate = true, parameters = parameters)

suspend fun<T> NoParamRepositoryProperty<T>.execute() = load(forceUpdate = true)