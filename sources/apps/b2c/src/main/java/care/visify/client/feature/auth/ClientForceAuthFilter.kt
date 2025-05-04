package care.visify.client.feature.auth

import androidx.core.text.isDigitsOnly
import care.visify.core.api.http.auth.ForceAuthFilter
import okhttp3.Request
import javax.inject.Inject

class ClientForceAuthFilter @Inject constructor() : ForceAuthFilter {

    //requests only for auth users
    private val requestsWhitelist = listOf(
        "/org/favourites/unset/",
        "/org/favourites/set",
        "/masters/favourite",
        //"/order/client/market/create",
        "/order/client/manual/create",
        "/order/client/get",
        "/photo/upload",
    )

    override fun isNeedToAuth(request: Request): Boolean {
        val requestPath = request.url
            .pathSegments
            .dropLast(1) // drop version
            .filter { it.isDigitsOnly().not() } // drop some path ids
            .joinToString(separator = "/", prefix = "/")
        return requestsWhitelist.any { requestPath.contains(it) }
    }
}