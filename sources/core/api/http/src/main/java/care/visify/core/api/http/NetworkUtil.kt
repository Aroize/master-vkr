package care.visify.core.api.http

import android.net.Uri
import care.visify.core.api.http.base.ApiHostManager

object NetworkUtil {

    private const val SCHEME = "https"

    @JvmStatic
    fun baseUrl(apiHostManager: ApiHostManager): String {
        return Uri.Builder()
            .scheme(SCHEME)
            .encodedAuthority(apiHostManager.getHost().host)
            .build()
            .toString()
    }
}