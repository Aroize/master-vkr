package care.visify.business.core.network

import care.visify.business.core.network.interceptors.BusinessInterceptorCollector
import care.visify.core.api.http.interceptor.InterceptorCollector
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkInterceptorModule {
    @Binds
    @Singleton
    abstract fun bindInterceptorProvider(
        impl: BusinessInterceptorCollector
    ): InterceptorCollector
}