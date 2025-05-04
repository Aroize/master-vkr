package care.visify.ui.kit.components.image

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import care.visify.core.image.Image
import care.visify.ui.kit.image.LocalImageUrlFormatter
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.integration.compose.RequestBuilderTransform
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition


@Composable
fun VisifyImageById(
    model: Image,
    modifier: Modifier = Modifier,
    shimmerModifier: Modifier = modifier,
    contentScale: ContentScale = ContentScale.Fit,
    requestBuilderTransform: RequestBuilderTransform<Drawable> = { it }
) {
    val imageUrlFormatter = LocalImageUrlFormatter.current
    @Suppress("IMPLICIT_CAST_TO_ANY") val source = when {
        model.isLocal -> model.unsafeFile
        model.isUri -> model.unsafeUri
        model.isRemote -> imageUrlFormatter.formatPublic(model.unsafeId)
        else -> model
    }

    VisifyImage(
        model = source,
        modifier = modifier,
        shimmerModifier = shimmerModifier,
        contentScale = contentScale,
        requestBuilderTransform = requestBuilderTransform
    )
}

@Composable
fun rememberGlideBitmap(
    model: Image,
    requestBuilderTransform: RequestBuilderTransform<Bitmap> = { it }
): Bitmap {
    val requestManager: RequestManager = LocalContext.current.let { remember(it) { Glide.with(it) } }
    var bitmap by remember {
        mutableStateOf(Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888))
    }

    val target = remember {
        object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) {
                bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
            }
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmap = resource
            }
        }
    }

    //todo зависимость от clientTheme
    val imageUrlFormatter = LocalImageUrlFormatter.current
    @Suppress("IMPLICIT_CAST_TO_ANY") val source = when {
        model.isLocal -> model.unsafeFile
        model.isUri -> model.unsafeUri
        model.isRemote -> imageUrlFormatter.formatPublic(model.unsafeId)
        else -> model
    }

    requestBuilderTransform(
        requestManager
            .asBitmap()
            .load(source)
    )
        .into(target)

    return bitmap
}