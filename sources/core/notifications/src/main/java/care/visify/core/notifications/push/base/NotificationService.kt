package care.visify.core.notifications.push.base

import care.visify.api.models.notifications.TokenRequest
import care.visify.core.api.http.ContentType
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotificationService {
    @POST("/notifications/register/v1")
    @Headers(ContentType.Application.Json)
    suspend fun registerFcmToken(@Body body: TokenRequest)

    @POST("/notifications/unregister/v1")
    @Headers(ContentType.Application.Json)
    suspend fun unregisterFcmToken(@Body body: TokenRequest)

}