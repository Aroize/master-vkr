package care.visify.core.ktx

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CoroutineDispatchersModule {
    @Binds
    @Singleton
    abstract fun bindCoroutineDispatchers(
        coroutineDispatchersImpl: CoroutineDispatchersImpl
    ): CoroutineDispatchers
}

@Module
@InstallIn(SingletonComponent::class)
object CoroutineScopeModule {
    @Provides
    fun provideCoroutineScope(
        dispatchers: CoroutineDispatchers
    ): CoroutineScope = CoroutineScope(SupervisorJob() + dispatchers.io)
}