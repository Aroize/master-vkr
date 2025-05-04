package care.visify.core.image.upload

import care.visify.api.models.common.CollectionResponse
import care.visify.api.models.common.ImageResponse
import care.visify.core.ktx.CoroutineDispatchers
import care.visify.core.repository.Parameters
import care.visify.core.repository.RepositoryProperty
import care.visify.core.repository.execute
import care.visify.core.repository.noCacheRepositoryProperty
import kotlinx.coroutines.withContext
import okhttp3.RequestBody
import javax.inject.Inject

interface UploadImageRepository {
    suspend fun upload(body: RequestBody): CollectionResponse<ImageResponse>
}

private data class BodyParameters(val body: RequestBody): Parameters

class UploadImageRepositoryImpl @Inject constructor(
    private val dispatchers: CoroutineDispatchers,
    private val service: UploadImageService
): UploadImageRepository {

    private val uploadProperty: RepositoryProperty<CollectionResponse<ImageResponse>, BodyParameters>
        = noCacheRepositoryProperty(
            remoteSource = { service.upload(it.body) }
        )

    override suspend fun upload(
        body: RequestBody
    ): CollectionResponse<ImageResponse> = withContext(dispatchers.io) {
        uploadProperty.execute(BodyParameters(body))
    }
}