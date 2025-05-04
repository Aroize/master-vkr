package care.visify.core.location.hilt

import care.visify.core.location.helper.LocationHelper
import care.visify.core.location.helper.LocationHelperHolder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    @Provides
    fun provideLocationHelper(): LocationHelper
        = LocationHelperHolder
            .obtain()
}