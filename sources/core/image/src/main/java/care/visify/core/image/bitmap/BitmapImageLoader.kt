package care.visify.core.image.bitmap

import android.graphics.Bitmap
import java.util.UUID

interface BitmapImageLoader {
    suspend fun bitmapById(imageId: UUID): Bitmap
}