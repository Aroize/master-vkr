package care.visify.core.image.upload

import care.visify.core.image.Image
import care.visify.core.storage.ImageFileManager
import care.visify.core.storage.ImageFrom
import care.visify.core.storage.ImageModifier
import care.visify.core.storage.ImageTransformer
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

class UploadImageHelper @Inject constructor(
    private val imageFileManager: ImageFileManager,
    private val uploadImageManager: UploadImageManager,
) {
    suspend fun uploadImagesIfNecessary(images: List<Image>): List<Image> {
        return images.map { image ->
            when {
                image.isRemote -> image
                else -> uploadImage(image)
            }
        }
    }

    private suspend fun uploadImage(image: Image): Image {
        val file = when {
            image.isLocal -> image.unsafeFile
            else -> imageFileManager.transform(
                ImageTransformer(
                    from = ImageFrom.Uri(image.unsafeUri),
                    modifiers = listOf(ImageModifier.SCALE)
                )
            )
        }
        val result = uploadImageManager.uploadImages(file)
            .toList()
        val uploadedImage = result
            .filterIsInstance<ImageRequest.Succeed>()
            .first()

        return Image(uploadedImage.photos.items.first())
    }
}