package care.visify.core.storage.converters

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.SystemClock
import care.visify.core.storage.FileManager
import care.visify.core.storage.ImageFormat
import care.visify.core.storage.ImageFormatConverter
import care.visify.core.util.atLeastR
import java.io.File
import java.io.FileOutputStream

internal class WebpImageConverter(
    private val fileManager: FileManager,
) : ImageFormatConverter {

    override val format: ImageFormat = ImageFormat.WEBP

    @SuppressLint("NewApi")
    override fun convert(file: File): File {
        val bitmap = BitmapFactory.decodeFile(file.absolutePath)
        val webpFile = File(file.parent, "${file.nameWithoutExtension}.webp")
        FileOutputStream(webpFile).use { output ->
            atLeastR(
                action = {
                    bitmap.compress(Bitmap.CompressFormat.WEBP_LOSSLESS, 100, output)
                },
                fallback = {
                    bitmap.compress(Bitmap.CompressFormat.WEBP, 100, output)
                }
            )
        }
        return webpFile
    }

    @SuppressLint("NewApi")
    override fun convertFromBitmap(bitmap: Bitmap): File {
        val file = fileManager.createFile(
            scope = FileManager.Scope.IMAGE,
            filename = "${SystemClock.elapsedRealtime()}.webp"
        )
        FileOutputStream(file).use { output ->
            atLeastR(
                action = {
                    bitmap.compress(Bitmap.CompressFormat.WEBP_LOSSLESS, 100, output)
                },
                fallback = {
                    bitmap.compress(Bitmap.CompressFormat.WEBP, 100, output)
                }
            )
        }
        return file
    }
}