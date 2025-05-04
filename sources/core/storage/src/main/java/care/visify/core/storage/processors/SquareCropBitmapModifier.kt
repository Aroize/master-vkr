package care.visify.core.storage.processors

import android.graphics.Bitmap
import care.visify.core.storage.BitmapModifier
import care.visify.core.storage.ImageModifier

internal class SquareCropBitmapModifier : BitmapModifier {

    override val type: ImageModifier = ImageModifier.CROP_TO_SQUARE

    override fun transform(bitmap: Bitmap): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        val newDimension = if (width < height) width else height

        val x: Int = (width - newDimension) / 2
        val y: Int = (height - newDimension) / 2

        return Bitmap.createBitmap(bitmap, x, y, newDimension, newDimension)
    }
}