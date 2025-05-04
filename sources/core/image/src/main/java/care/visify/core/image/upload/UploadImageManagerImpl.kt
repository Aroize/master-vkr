package care.visify.core.image.upload

import care.visify.api.models.common.CollectionResponse
import care.visify.core.storage.ImageFileManager
import care.visify.core.storage.ImageFrom
import care.visify.core.storage.ImageModifier
import care.visify.core.storage.ImageTransformer
import care.visify.core.storage.mimeType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOf
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.IOException
import javax.inject.Inject

class UploadImageManagerImpl @Inject constructor(
    private val repository: UploadImageRepository,
    private val imageFileManager: ImageFileManager
) : UploadImageManager {
    override suspend fun uploadImages(vararg files: File): Flow<ImageRequest> {
        if (files.isEmpty()) return flowOf(
            ImageRequest.Succeed(
                CollectionResponse(emptyList(), 0)
            )
        )
        if (files.size > MAX_ITEMS_COUNT) return flowOf(
            ImageRequest.Failure(IllegalArgumentException("Can't process more than $MAX_ITEMS_COUNT files"))
        )
        if (files.any { it.exists().not() }) return flowOf(
            ImageRequest.Failure(IOException("Provided image doesn't exist"))
        )

        val scaledFiles = files.map { file ->
            imageFileManager.transform(
                ImageTransformer(
                    from = ImageFrom.File(file),
                    modifiers = listOf(ImageModifier.SCALE)
                )
            )
        }

        val parts = scaledFiles.map { file ->
            MultipartBody.Part.createFormData(
                PHOTOS_ARG,
                file.name,
                file.asRequestBody(contentType = file.mimeType.toMediaTypeOrNull())
            )
        }
        val multipart = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .apply { parts.forEach { addPart(it) } }
            .build()

        return callbackFlow {
            val listener = UploadProgressListener { trySend(ImageRequest.Progress(it)) }

            val progressBody = MediaRequestBodyWithProgress(multipart, listener)

            runCatching { repository.upload(progressBody) }.fold(
                onSuccess = { response -> send(ImageRequest.Succeed(response)) },
                onFailure = { error -> send(ImageRequest.Failure(error)) }
            ).also { close() }
        }
    }

    private companion object {
        const val PHOTOS_ARG = "photos"
        const val MAX_ITEMS_COUNT = 10
    }
}