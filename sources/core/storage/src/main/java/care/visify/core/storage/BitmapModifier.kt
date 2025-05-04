package care.visify.core.storage

import android.graphics.Bitmap

interface BitmapModifier {
    val type: ImageModifier
    fun transform(bitmap: Bitmap): Bitmap
}