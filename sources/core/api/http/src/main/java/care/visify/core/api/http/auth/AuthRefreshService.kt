package care.visify.core.api.http.auth

import care.visify.api.models.auth.TokensResponse
import care.visify.core.api.http.ClientHeaders
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthRefreshService {
    @POST("/auth/refresh/v1")
    suspend fun refresh(
        @Header(ClientHeaders.ACCESS_HEADER)
        access: String,
        @Header(ClientHeaders.REFRESH_HEADER)
        refresh: String
    ): TokensResponse
}