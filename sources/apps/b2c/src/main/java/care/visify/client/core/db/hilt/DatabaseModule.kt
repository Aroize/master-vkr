package care.visify.client.core.db.hilt

import android.content.Context
import androidx.room.Room
import care.visify.client.core.db.ClientDatabase
import care.visify.client.core.db.ClientDatabaseConstants
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
        @ApplicationContext context: Context
    ): ClientDatabase = Room.databaseBuilder(
        context,
        ClientDatabase::class.java,
        ClientDatabaseConstants.NAME
    ).build()
}