package care.visify.client.feature.auth.logout

import care.visify.feature.auth.api.OnLogoutCallback
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LogoutModule {
    @Binds
    abstract fun bindOnLogoutCallback(impl: ClientOnLogoutCallback): OnLogoutCallback
}