package care.visify.core.storage.processors

import android.graphics.Bitmap
import androidx.core.graphics.scale
import care.visify.core.storage.BitmapModifier
import care.visify.core.storage.ImageModifier

internal class ScaleBitmapModifier : BitmapModifier {

    override val type: ImageModifier = ImageModifier.SCALE

    override fun transform(bitmap: Bitmap): Bitmap {
        val biggestSide = maxOf(bitmap.width, bitmap.height)
        if (biggestSide < MAX_SERVER_IMAGE_SIDE) return bitmap
        val scale = MAX_SERVER_IMAGE_SIDE.toFloat() / biggestSide

        val outHeight = bitmap.height * scale
        val outWidth = bitmap.width * scale

        return bitmap.scale(outWidth.toInt(), outHeight.toInt())
    }

    companion object {
        const val MAX_SERVER_IMAGE_SIDE = 2048
    }
}