package care.visify.business.core.compose

import androidx.compose.runtime.ProvidedValue
import care.visify.core.image.formatter.ImageUrlFormatter
import care.visify.ui.kit.fragment.CompositionProvider
import care.visify.ui.kit.image.LocalImageUrlFormatter
import javax.inject.Inject

class BusinessCompositionProvider @Inject constructor(
    private val imageUrlFormatter: ImageUrlFormatter
) : CompositionProvider {

    override fun provideCompositions(
    ): Array<ProvidedValue<*>> {
        return arrayOf(
            LocalImageUrlFormatter provides imageUrlFormatter
        )
    }
}