package care.visify.core.api.http.interceptor

import okhttp3.Interceptor

interface InterceptorCollector {
    fun provideInterceptors(): List<Interceptor>
}