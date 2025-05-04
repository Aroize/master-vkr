package care.visify.business.core.notifications

import care.visify.core.notifications.intent.IntentHandler
import care.visify.core.notifications.intent.IntentProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class BusinessIntentModule {
    @Binds
    abstract fun bindsBusinessIntentHandler(
        impl: BusinessIntentHandler,
    ): IntentHandler

    @Binds
    abstract fun bindsBusinessIntentProvider(
        impl: BusinessIntentProvider,
    ): IntentProvider

}