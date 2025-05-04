package care.visify.core.storage

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.SystemClock
import androidx.core.net.toFile
import care.visify.core.ktx.CoroutineDispatchers
import care.visify.core.storage.converters.ImageConverterHolder
import care.visify.core.storage.processors.BitmapModifierHolder
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject


interface ImageFileManager {
    suspend fun transform(transformer: ImageTransformer): File

    fun createPlainImage(): File
    fun fileByName(filename: String): File
}

class ImageFileManagerImpl @Inject constructor(
    private val fileManager: FileManager,
    private val dispatchers: CoroutineDispatchers,
    @ApplicationContext private val context: Context,
) : ImageFileManager {

    private val converterHolder = ImageConverterHolder(fileManager)
    private val modifierHolder = BitmapModifierHolder()

    override fun fileByName(filename: String): File {
        val imageDirectory = fileManager.scopedDirectory(FileManager.Scope.IMAGE)
        return File(imageDirectory, filename)
    }

    override fun createPlainImage(): File {
        return fileManager.createFile(
            scope = FileManager.Scope.IMAGE,
            filename = "${SystemClock.elapsedRealtime()}"
        ).apply { require(exists()) }
    }

    override suspend fun transform(transformer: ImageTransformer): File =
        withContext(dispatchers.io) {
            if (transformer.isNeedToModify) {
                var bitmap = when (val model = transformer.from) {
                    is ImageFrom.Bitmap -> model.bitmap
                    is ImageFrom.File -> BitmapFactory.decodeFile(model.file.absolutePath)
                    is ImageFrom.Uri -> {
                        val inputStream = context.contentResolver.openInputStream(model.uri)
                        BitmapFactory.decodeStream(inputStream)
                    }
                }

                transformer.modifiers.forEach {
                    bitmap = modifierHolder.get(it).transform(bitmap)
                }

                saveBitmapToFile(bitmap, transformer.toImageFormat)
            } else {
                when (val model = transformer.from) {
                    is ImageFrom.Bitmap -> saveBitmapToFile(
                        model.bitmap,
                        transformer.toImageFormat
                    )

                    is ImageFrom.File -> model.file
                    is ImageFrom.Uri -> createImageFromUri(
                        model.uri,
                        transformer.toImageFormat
                    )
                }
            }
        }

    private suspend fun createImageFromUri(
        uri: Uri,
        format: ImageFormat,
    ): File {
        val converter = converterHolder.get(format)
        if (uri.scheme == "file")
            return converter.convert(uri.toFile())
        val imageFile = fileManager.createFile(
            uri = uri,
            scope = FileManager.Scope.IMAGE,
            filename = "${SystemClock.elapsedRealtime()}"
        )
        require(imageFile.exists() && imageFile.length() > 0)
        val convertedFile = converter.convert(imageFile)
        imageFile.delete()
        return convertedFile
    }

    @SuppressLint("NewApi")
    private fun saveBitmapToFile(bitmap: Bitmap, format: ImageFormat): File {
        val file = createPlainImage()
        FileOutputStream(file).use { output ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, output)
        }
        return converterHolder.get(format).convert(file)
    }
}