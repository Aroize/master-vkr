package care.visify.core.pref.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import care.visify.core.pref.PreferencesFactory
import care.visify.core.pref.PreferencesFactoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferencesModule {
    @Binds
    @Singleton
    abstract fun bindPreferencesFactory(
        preferencesFactory: PreferencesFactoryImpl
    ): PreferencesFactory
}