package care.visify.business.feature.auth

import care.visify.feature.auth.api.nav.FinishRegistrationNavigation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class BusinessFinishRegistrationNavModule {

    @Binds
    abstract fun bindSelectCityNavigation(
        impl: BusinessFinishRegistrationNavigation,
    ): FinishRegistrationNavigation
}