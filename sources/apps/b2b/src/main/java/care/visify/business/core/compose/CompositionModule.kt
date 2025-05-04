package care.visify.business.core.compose

import care.visify.core.image.formatter.ImageUrlFormatter
import care.visify.ui.kit.fragment.CompositionProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object CompositionModule {
    @Provides
    fun provideClientCompositionProvider(
        imageUrlFormatter: ImageUrlFormatter
    ): CompositionProvider = BusinessCompositionProvider(imageUrlFormatter)
}