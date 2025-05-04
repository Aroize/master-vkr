package care.visify.client.feature.catalog

import care.visify.client.core.db.ClientDatabase
import care.visify.feature.catalog.impl.dao.SearchHistoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CatalogDatabaseModule {
    @Provides
    @Singleton
    fun provideSearchHistoryDao(
        database: ClientDatabase,
    ): SearchHistoryDao = database.searchHistoryDao()
}