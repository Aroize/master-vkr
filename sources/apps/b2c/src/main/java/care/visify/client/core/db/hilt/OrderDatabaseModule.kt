package care.visify.client.core.db.hilt

import care.visify.client.core.db.ClientDatabase
import care.visify.feature.order.domain.market.dao.PendingOrderDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OrderDatabaseModule {

    @Provides
    @Singleton
    fun providePendingOrderDao(
        database: ClientDatabase,
    ): PendingOrderDao = database.pendingOrderDao()
}
