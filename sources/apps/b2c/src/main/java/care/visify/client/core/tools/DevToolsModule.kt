package care.visify.client.core.tools

import care.visify.feature.debug.api.EventingIdsInvalidator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DevToolsModule {
    @Binds
    @Singleton
    abstract fun bindEventing(
        impl: EventingIdsInvalidatorImpl
    ): EventingIdsInvalidator
}