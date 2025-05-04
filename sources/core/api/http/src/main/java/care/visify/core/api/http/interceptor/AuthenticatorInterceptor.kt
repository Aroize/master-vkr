package care.visify.core.api.http.interceptor

import care.visify.core.api.http.auth.AuthTokenManager
import care.visify.core.api.http.toBearer
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticatorInterceptor(
    private val tokenManager: AuthTokenManager
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = tokenManager.access()
        val refreshToken = tokenManager.refresh()
        if (accessToken.isBlank() || refreshToken.isBlank()) {
            return chain.proceed(chain.request())
        }
        val request = chain.request().newBuilder()
            .header(ACCESS_HEADER, accessToken.toBearer())
            .header(REFRESH_HEADER, refreshToken.toBearer())
            .build()
        return chain.proceed(request)
    }

    companion object {
        private const val ACCESS_HEADER = "Authorization"
        private const val REFRESH_HEADER = "X-Authorization-Refresh"
    }
}