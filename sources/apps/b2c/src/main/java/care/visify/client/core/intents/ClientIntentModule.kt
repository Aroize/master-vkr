package care.visify.client.core.intents

import care.visify.core.notifications.intent.IntentHandler
import care.visify.core.notifications.intent.IntentProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class ClientIntentModule {

    @Binds
    abstract fun bindsClientIntentHandler(
        impl: ClientIntentHandler,
    ): IntentHandler

    @Binds
    abstract fun bindsClientIntentProvider(
        impl: ClientIntentProvider,
    ): IntentProvider

}