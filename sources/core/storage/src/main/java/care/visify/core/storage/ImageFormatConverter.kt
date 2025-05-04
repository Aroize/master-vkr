package care.visify.core.storage

import android.graphics.Bitmap
import java.io.File

interface ImageFormatConverter {
    val format: ImageFormat
    fun convert(file: File): File
    fun convertFromBitmap(bitmap: Bitmap) : File
}