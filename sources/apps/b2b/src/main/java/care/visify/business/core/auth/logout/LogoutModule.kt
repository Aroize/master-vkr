package care.visify.business.core.auth.logout

import care.visify.feature.auth.api.OnLogoutCallback
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LogoutModule {
    @Binds
    abstract fun bindOnLogoutCallback(impl: BusinessOnLogoutCallback): OnLogoutCallback
}