package care.visify.ui.kit.components.image

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.ContentScale
import care.visify.ui.kit.theme.VisifyTheme
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideSubcomposition
import com.bumptech.glide.integration.compose.RequestBuilderTransform
import com.bumptech.glide.integration.compose.RequestState
import com.valentinilk.shimmer.shimmer

@Composable
@OptIn(ExperimentalGlideComposeApi::class)
fun VisifyImage(
    model: Any,
    modifier: Modifier = Modifier,
    shimmerModifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    requestBuilderTransform: RequestBuilderTransform<Drawable> = { it },
    onImageSizeLoaded: (Size) -> Unit = {  }
) {
    GlideSubcomposition(
        model = model,
        requestBuilderTransform = requestBuilderTransform,
        modifier = modifier
    ) {
        when (state) {
            RequestState.Failure -> Unit // todo
            RequestState.Loading -> Box(
                modifier = shimmerModifier
                    .shimmer()
                    .background(VisifyTheme.colors.frame.dialogOverlay)
            )
            is RequestState.Success -> {
                onImageSizeLoaded(painter.intrinsicSize)
                Image(
                    painter,
                    contentScale = contentScale,
                    modifier = modifier,
                    contentDescription = null
                )
            }
        }
    }
}
