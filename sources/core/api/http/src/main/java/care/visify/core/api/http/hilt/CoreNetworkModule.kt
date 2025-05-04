package care.visify.core.api.http.hilt

import android.content.Context
import care.visify.core.api.http.NetworkUtil
import care.visify.core.api.http.auth.AppAuthListener
import care.visify.core.api.http.auth.AuthRefreshService
import care.visify.core.api.http.auth.AuthServiceProxy
import care.visify.core.api.http.auth.AuthTokenManager
import care.visify.core.api.http.auth.AuthTokenManagerImpl
import care.visify.core.api.http.auth.ClientAuthenticator
import care.visify.core.api.http.auth.ForceAuthFilter
import care.visify.core.api.http.base.ApiHostManager
import care.visify.core.api.http.base.DefaultHttpClientProvider
import care.visify.core.api.http.base.HttpClientProvider
import care.visify.core.api.http.base.LocalhostHostnameVerifierManager
import care.visify.core.api.http.connectivity.NetworkManager
import care.visify.core.api.http.flipper.FlipperNetworkHolder
import care.visify.core.api.http.interceptor.AppTypeInterceptor
import care.visify.core.api.http.interceptor.AuthenticatorInterceptor
import care.visify.core.api.http.interceptor.InterceptorCollector
import care.visify.core.api.http.interceptor.LogInterceptorProvider
import care.visify.core.bus.EventBus
import care.visify.core.configuration.Configuration
import care.visify.core.ktx.CoroutineDispatchers
import care.visify.core.pref.PreferencesFactory
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Authenticator
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreNetworkModule {

    @Provides
    @AuthenticatorInterceptorQualifier
    fun provideAuthenticatorInterceptor(
        tokenManager: AuthTokenManager,
    ): Interceptor = AuthenticatorInterceptor(tokenManager)


    @Provides
    @LoggingInterceptorQualifier
    fun provideLoggingInterceptor(): Interceptor = LogInterceptorProvider.interceptor()

    @Provides
    @FlipperInterceptorQualifier
    fun provideFlipperInterceptor(): Interceptor =
        FlipperOkhttpInterceptor(FlipperNetworkHolder.plugin, true)

    @Provides
    @AppTypeInterceptorQualifier
    fun provideAppTypeInterceptor(
        configuration: Configuration,
    ): Interceptor = AppTypeInterceptor(configuration)

    @Provides
    @Singleton
    fun provideTokenManager(
        preferencesFactory: PreferencesFactory,
    ): AuthTokenManager = AuthTokenManagerImpl(preferencesFactory)

    @Provides
    @Singleton
    fun provideApiHostManager(
        preferencesFactory: PreferencesFactory,
    ): ApiHostManager = ApiHostManager(preferencesFactory)

    @Provides
    @Singleton
    fun provideLocalhostHostnameVerifierManager(
        preferencesFactory: PreferencesFactory,
    ): LocalhostHostnameVerifierManager = LocalhostHostnameVerifierManager(preferencesFactory)

    @Provides
    @Singleton
    fun provideAuthServiceProxy(
        dispatchers: CoroutineDispatchers,
        authRefreshService: AuthRefreshService,
        tokenManager: AuthTokenManager,
        appAuthListener: AppAuthListener,
        forceAuthFilter: ForceAuthFilter,
        eventBus: EventBus,
    ): AuthServiceProxy = AuthServiceProxy(
        dispatchers, authRefreshService, tokenManager, appAuthListener,
        forceAuthFilter = forceAuthFilter, eventBus
    )

    @Provides
    @Singleton
    @DefaultHttpClientQualifier
    fun provideDefaultHttpClientProvider(
        interceptorCollector: InterceptorCollector,
        authenticator: Authenticator,
        verifierManager: LocalhostHostnameVerifierManager,
    ): HttpClientProvider = DefaultHttpClientProvider(
        interceptorCollector, authenticator, verifierManager
    )

    @Provides
    @Singleton
    fun provideAuthenticator(
        authServiceProxy: AuthServiceProxy,
        tokenManager: AuthTokenManager,
    ): Authenticator = ClientAuthenticator(authServiceProxy, tokenManager)

    @Provides
    @Singleton
    fun provideRetrofit(
        @DefaultHttpClientQualifier httpClientProvider: HttpClientProvider,
        apiHostManager: ApiHostManager,
        gson: Gson,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(NetworkUtil.baseUrl(apiHostManager))
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(httpClientProvider.client)
        .build()

    @Provides
    @Singleton
    fun provideAuthRefreshService(
        apiHostManager: ApiHostManager,
        gson: Gson,
    ): AuthRefreshService = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(NetworkUtil.baseUrl(apiHostManager))
        .build()
        .create(AuthRefreshService::class.java)


    @Provides
    @Singleton
    fun provideNetworkManager(
        @ApplicationContext context: Context,
        bus: EventBus,
    ): NetworkManager = NetworkManager(context, bus)

}