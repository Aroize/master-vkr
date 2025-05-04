package care.visify.core.image.formatter

import android.net.Uri
import androidx.core.net.toUri
import care.visify.core.api.http.NetworkUtil
import care.visify.core.api.http.base.ApiHostManager
import com.bumptech.glide.load.model.GlideUrl
import java.util.UUID
import javax.inject.Inject


class ImageUrlFormatterImpl @Inject constructor(
    private val apiHostManager: ApiHostManager
): ImageUrlFormatter {

    override fun formatPublic(id: UUID): GlideUrl {
        val baseUrl = NetworkUtil.baseUrl(apiHostManager).toUri()

        val imageUrl = baseUrl
            .withAppended(PUBLIC_IMG_ENDPOINT)
            .withAppended(id.toString())
            .withAppended(GET_PHOTO_METHOD)
            .withAppended(PHOTO_METHOD_VERSION)

        return GlideUrl(imageUrl.toString())
    }

    private fun Uri.withAppended(segment: String): Uri
        = Uri.withAppendedPath(this, segment)

    private companion object {
        const val SCHEME = "https"
        const val PUBLIC_IMG_ENDPOINT = "photo"
        const val GET_PHOTO_METHOD = "get"
        const val PHOTO_METHOD_VERSION = "v1"
    }
}