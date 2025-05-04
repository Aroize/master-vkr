package care.visify.business.core.auth.hilt

import care.visify.business.core.auth.BusinessForceAuthFilter
import care.visify.business.core.auth.BusinessGoogleSignInTokenProvider
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
        impl: BusinessGoogleSignInTokenProvider,
    ): GoogleSignInTokenProvider

    @Binds
    @Singleton
    abstract fun bindAuthForceWhiteList(
        impl: BusinessForceAuthFilter,
    ): ForceAuthFilter
}