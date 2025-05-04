package care.visify.client.core.configuration

import care.visify.client.BuildConfig
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
    fun provideClientConfiguration(): Configuration =
        Configuration(BuildConfig.VERSION_NAME, AppType.CLIENT)

}