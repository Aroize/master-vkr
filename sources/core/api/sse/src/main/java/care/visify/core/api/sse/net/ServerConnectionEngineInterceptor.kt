package care.visify.core.api.sse.net

import care.visify.core.api.sse.engine.ServerConnectionEngine
import okhttp3.Interceptor
import okhttp3.Response

class ServerConnectionEngineInterceptor(
    private val engine: ServerConnectionEngine
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.isSuccessful) {
            engine.start()
        }
        return response
    }
}