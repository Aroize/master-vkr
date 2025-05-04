package care.visify.core.notifications.intent

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class IntentModule {

    @Binds
    abstract fun bindsPendingIntentProvider(
        impl: PendingIntentProviderImpl,
    ): PendingIntentProvider

}