package care.visify.business.core.db.hilt

import android.content.Context
import androidx.room.Room
import care.visify.business.core.db.BusinessDatabase
import care.visify.business.core.db.BusinessDatabaseConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): BusinessDatabase = Room.databaseBuilder(
        context,
        BusinessDatabase::class.java,
        BusinessDatabaseConstants.NAME
    ).build()
}