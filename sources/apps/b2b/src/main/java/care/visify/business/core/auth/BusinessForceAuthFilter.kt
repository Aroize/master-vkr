package care.visify.business.core.auth

import androidx.core.text.isDigitsOnly
import care.visify.core.api.http.auth.ForceAuthFilter
import okhttp3.Request
import javax.inject.Inject

class BusinessForceAuthFilter @Inject constructor() : ForceAuthFilter {

    //request available for NOT auth users
    private val requestsWhiteList = listOf(
        "/address/city/list/"
    )

    override fun isNeedToAuth(request: Request): Boolean {
        val requestPath = request.url
            .pathSegments
            .dropLast(1) // drop version
            .filter { it.isDigitsOnly().not() } // drop some path ids
            .joinToString(separator = "/", prefix = "/")

        return requestsWhiteList.any { requestPath.contains(it) }.not()
    }
}