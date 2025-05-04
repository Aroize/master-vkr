package care.visify.ui.kit.image

import androidx.compose.runtime.staticCompositionLocalOf
import care.visify.core.image.formatter.ImageUrlFormatter

val LocalImageUrlFormatter = staticCompositionLocalOf<ImageUrlFormatter> {
    error("No ImageUrlFormatter provided")
}