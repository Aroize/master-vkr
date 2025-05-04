package care.visify.client.core.connection

import care.visify.client.core.db.ClientDatabase
import care.visify.core.api.sse.repository.RemoteEventDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConnectionDatabaseModule {
    @Provides
    @Singleton
    fun provideRemoteEventDao(
        database: ClientDatabase
    ): RemoteEventDao = database.remoteEventDao()
}