package care.visify.core.image.upload

import kotlinx.coroutines.flow.Flow
import java.io.File


interface UploadImageManager {
    suspend fun uploadImages(vararg files: File): Flow<ImageRequest>
}