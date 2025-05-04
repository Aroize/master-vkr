package care.visify.client.core.net.interceptors

import care.visify.core.api.http.hilt.AppTypeInterceptorQualifier
import care.visify.core.api.http.hilt.AuthenticatorInterceptorQualifier
import care.visify.core.api.http.hilt.FlipperInterceptorQualifier
import care.visify.core.api.http.hilt.LoggingInterceptorQualifier
import care.visify.core.api.http.interceptor.InterceptorCollector
import care.visify.core.api.sse.hilt.ServerEngineQualifier
import okhttp3.Interceptor
import javax.inject.Inject

class ClientInterceptorCollector @Inject constructor(
    @AuthenticatorInterceptorQualifier private val authenticatorInterceptor: Interceptor,
    @LoggingInterceptorQualifier private val logging: Interceptor,
    @FlipperInterceptorQualifier private val flipper: Interceptor,
    @ServerEngineQualifier private val connection: Interceptor,
    @AppTypeInterceptorQualifier private val appType: Interceptor,
) : InterceptorCollector {
    override fun provideInterceptors(): List<Interceptor> {
        return listOf(
            authenticatorInterceptor,
            logging,
            connection,
            appType,
            //flipper always at the end
            flipper,
        )
    }
}