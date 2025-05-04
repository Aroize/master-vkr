package care.visify.core.storage.converters

import care.visify.core.storage.FileManager
import care.visify.core.storage.ImageFormat
import care.visify.core.storage.ImageFormatConverter

class ImageConverterHolder(
    private val fileManager: FileManager
) {
    private val converters = listOf<ImageFormatConverter>(WebpImageConverter(fileManager))

    fun get(format: ImageFormat): ImageFormatConverter {
        return converters.find { it.format == format }
            ?: throw IllegalArgumentException("No converters for format = $format")
    }
}