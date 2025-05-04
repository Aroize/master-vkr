package care.visify.client.feature.auth.nav

import care.visify.feature.auth.api.nav.FinishRegistrationNavigation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class ClientFinishRegistrationNavModule {

    @Binds
    abstract fun bindSelectCityNavigation(
        impl: ClientFinishRegistrationNavigation,
    ): FinishRegistrationNavigation
}