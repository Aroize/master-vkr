package care.visify.client.feature.auth.hilt

import care.visify.client.feature.auth.ClientForceAuthFilter
import care.visify.client.feature.auth.ClientGoogleSignInTokenProvider
import care.visify.core.api.http.auth.ForceAuthFilter
import care.visify.feature.auth.api.GoogleSignInTokenProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {
    @Binds
    @Singleton
    abstract fun bindGoogleSignInTokenProvider(
        impl: ClientGoogleSignInTokenProvider,
    ): GoogleSignInTokenProvider

    @Binds
    @Singleton
    abstract fun bindAuthForceWhiteList(
        impl: ClientForceAuthFilter,
    ): ForceAuthFilter
}