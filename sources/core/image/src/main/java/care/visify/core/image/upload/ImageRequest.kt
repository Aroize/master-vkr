package care.visify.core.image.upload

import care.visify.api.models.common.CollectionResponse
import care.visify.api.models.common.ImageResponse

sealed class ImageRequest {
    class Progress(val progress: Float): ImageRequest()
    class Succeed(val photos: CollectionResponse<ImageResponse>): ImageRequest()
    class Failure(val error: Throwable): ImageRequest()
}