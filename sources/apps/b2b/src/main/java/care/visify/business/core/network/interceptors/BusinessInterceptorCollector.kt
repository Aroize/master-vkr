package care.visify.business.core.network.interceptors

import care.visify.core.api.http.hilt.AppTypeInterceptorQualifier
import care.visify.core.api.http.hilt.AuthenticatorInterceptorQualifier
import care.visify.core.api.http.hilt.FlipperInterceptorQualifier
import care.visify.core.api.http.hilt.LoggingInterceptorQualifier
import care.visify.core.api.http.interceptor.InterceptorCollector
import okhttp3.Interceptor
import javax.inject.Inject

class BusinessInterceptorCollector @Inject constructor(
    @AuthenticatorInterceptorQualifier private val authenticatorInterceptor: Interceptor,
    @LoggingInterceptorQualifier private val logging: Interceptor,
    @FlipperInterceptorQualifier private val flipper: Interceptor,
    @AppTypeInterceptorQualifier private val appType : Interceptor
): InterceptorCollector {
    override fun provideInterceptors(): List<Interceptor> = listOf(
        authenticatorInterceptor,
        logging,
        appType,
        //flipper always at the and
        flipper,
    )
}