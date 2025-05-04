package care.visify.core.image.bitmap

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import care.visify.core.image.formatter.ImageUrlFormatter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.IOException
import java.util.UUID
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class BitmapImageLoaderImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val imageUrlFormatter: ImageUrlFormatter
): BitmapImageLoader {
    override suspend fun bitmapById(imageId: UUID): Bitmap = suspendCancellableCoroutine { continuation ->
        val url = imageUrlFormatter.formatPublic(imageId)
        Glide.with(context)
            .asBitmap()
            .load(url)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    if (continuation.isActive) {
                        continuation.resume(resource)
                    }
                }

                override fun onLoadCleared(placeholder: Drawable?) = Unit

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    if (continuation.isActive) {
                        continuation.resumeWithException(IOException("Image loading failed"))
                    }
                }
            })
    }
}