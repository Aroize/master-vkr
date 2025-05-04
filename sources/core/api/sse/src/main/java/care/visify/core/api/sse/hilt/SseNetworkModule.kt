package care.visify.core.api.sse.hilt

import care.visify.core.api.http.NetworkUtil
import care.visify.core.api.http.base.ApiHostManager
import care.visify.core.api.http.base.HttpClientProvider
import care.visify.core.api.http.base.LocalhostHostnameVerifierManager
import care.visify.core.api.http.hilt.AppTypeInterceptorQualifier
import care.visify.core.api.http.hilt.AuthenticatorInterceptorQualifier
import care.visify.core.api.sse.engine.ServerConnectionEngine
import care.visify.core.api.sse.net.ServerConnectionEngineInterceptor
import care.visify.core.api.sse.net.SseHttpClientProvider
import care.visify.core.api.sse.service.ConnectionService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Authenticator
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SseNetworkModule {
    @Provides
    @Singleton
    @ServerEngineQualifier
    fun provideServerConnectionEngineInterceptor(
        engine: ServerConnectionEngine,
    ): Interceptor = ServerConnectionEngineInterceptor(engine)

    @Provides
    @Singleton
    @SseHttpClientQualifier
    fun provideSseHttpClientProvider(
        @AuthenticatorInterceptorQualifier authenticatorInterceptor: Interceptor,
        @AppTypeInterceptorQualifier appTypeInterceptor: Interceptor,
        authenticator: Authenticator,
        localhostHostnameVerifierManager: LocalhostHostnameVerifierManager,
    ): HttpClientProvider = SseHttpClientProvider(
        listOf(authenticatorInterceptor, appTypeInterceptor),
        authenticator,
        localhostHostnameVerifierManager
    )

    @Provides
    @Singleton
    @SseRetrofitQualifier
    fun provideSseRetrofit(
        @SseHttpClientQualifier httpClientProvider: HttpClientProvider,
        apiHostManager: ApiHostManager,
        gson: Gson,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(NetworkUtil.baseUrl(apiHostManager))
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(httpClientProvider.client)
        .build()

    @Provides
    @Singleton
    fun provideConnectionService(
        @SseRetrofitQualifier retrofit: Retrofit,
    ): ConnectionService = retrofit.create()
}