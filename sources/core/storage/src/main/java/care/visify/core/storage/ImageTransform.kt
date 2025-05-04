package care.visify.core.storage

enum class ImageFormat { WEBP }
enum class ImageModifier { SCALE, CROP_TO_SQUARE }

sealed interface ImageFrom {
    class Uri(val uri: android.net.Uri) : ImageFrom
    class Bitmap(val bitmap: android.graphics.Bitmap) : ImageFrom
    class File(val file: java.io.File) : ImageFrom
}

data class ImageTransformer(
    val from: ImageFrom,
    val toImageFormat: ImageFormat = ImageFormat.WEBP,
    val modifiers: List<ImageModifier> = listOf(),
) {
    val isNeedToModify = modifiers.isNotEmpty()
}