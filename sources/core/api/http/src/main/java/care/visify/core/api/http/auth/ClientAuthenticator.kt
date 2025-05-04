package care.visify.core.api.http.auth

import care.visify.core.api.http.ClientHeaders
import care.visify.core.api.http.retryCount
import care.visify.core.api.http.toBearer
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

internal class ClientAuthenticator(
    private val authServiceProxy: AuthServiceProxy,
    private val tokenManager: AuthTokenManager
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? = when {
        checkDropNewRequest(response) -> null
        else -> runBlocking {
            val isTokenUpdateSucceed = authServiceProxy.updateTokens(response.request)
            if (isTokenUpdateSucceed.not())
                return@runBlocking null
            val access = tokenManager.access()
            val refresh = tokenManager.refresh()
            response.request.newBuilder()
                .header(ClientHeaders.ACCESS_HEADER, access.toBearer())
                .header(ClientHeaders.REFRESH_HEADER, refresh.toBearer())
                .build()
        }
    }

    private fun checkDropNewRequest(response: Response): Boolean {
        // it is first request so ---> false
        if (response.priorResponse == null) return false
        return response.retryCount >= RETRY_CONSTRAINT
    }

    companion object {
        private const val RETRY_CONSTRAINT = 1
    }
}