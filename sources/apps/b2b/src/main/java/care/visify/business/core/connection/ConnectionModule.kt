package care.visify.business.core.connection

import care.visify.business.core.db.BusinessDatabase
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
        database: BusinessDatabase,
    ): RemoteEventDao = database.remoteEventDao()
}