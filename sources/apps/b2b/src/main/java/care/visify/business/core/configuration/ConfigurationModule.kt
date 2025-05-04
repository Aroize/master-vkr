package care.visify.business.core.configuration


import care.visify.business.BuildConfig
import care.visify.core.configuration.AppType
import care.visify.core.configuration.Configuration
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ConfigurationModule {

    @Provides
    fun provideBusinessConfiguration(): Configuration =
        Configuration(BuildConfig.VERSION_NAME, AppType.BUSINESS)

}