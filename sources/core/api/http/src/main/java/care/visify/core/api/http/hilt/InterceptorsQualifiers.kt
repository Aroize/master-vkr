package care.visify.core.api.http.hilt

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthenticatorInterceptorQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LoggingInterceptorQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FlipperInterceptorQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AppTypeInterceptorQualifier