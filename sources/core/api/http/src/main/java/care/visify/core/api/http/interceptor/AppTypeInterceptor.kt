package care.visify.core.api.http.interceptor

import care.visify.core.configuration.AppType
import care.visify.core.configuration.Configuration
import okhttp3.Interceptor
import okhttp3.Response

class AppTypeInterceptor(
    private val configuration: Configuration,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val headerTypeValue = when (configuration.appType) {
            AppType.CLIENT -> CLIENT_TYPE_HEADER
            AppType.BUSINESS -> BUSINESS_TYPE_HEADER
        }

        val request = chain.request().newBuilder()
            .header(APP_TYPE_HEADER_KEY, headerTypeValue)
            .build()
        return chain.proceed(request)
    }

    companion object {
        private const val APP_TYPE_HEADER_KEY = "X-App-Type"

        private const val CLIENT_TYPE_HEADER = "CLIENT"
        private const val BUSINESS_TYPE_HEADER = "BUSINESS"
    }
}