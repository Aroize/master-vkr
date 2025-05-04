package care.visify.core.image.upload

import care.visify.api.models.common.CollectionResponse
import care.visify.api.models.common.ImageResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface UploadImageService {
    @POST("/photo/upload/v1")
    suspend fun upload(@Body body: RequestBody): CollectionResponse<ImageResponse>
}