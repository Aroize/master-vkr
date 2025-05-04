package care.visify.core.bus.hilt

import care.visify.core.bus.EventBus
import care.visify.core.bus.EventBusImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EventBusModule {
    @Provides
    @Singleton
    fun provideEventBus(): EventBus = EventBusImpl()
}