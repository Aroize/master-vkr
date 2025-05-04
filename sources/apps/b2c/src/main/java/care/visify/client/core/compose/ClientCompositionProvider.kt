package care.visify.client.core.compose

import androidx.compose.runtime.ProvidedValue
import care.visify.core.image.formatter.ImageUrlFormatter
import care.visify.ui.kit.fragment.CompositionProvider
import care.visify.ui.kit.image.LocalImageUrlFormatter
import javax.inject.Inject

class ClientCompositionProvider @Inject constructor(
    private val imageUrlFormatter: ImageUrlFormatter
) : CompositionProvider {

    override fun provideCompositions(): Array<ProvidedValue<*>> {
        return arrayOf(
            LocalImageUrlFormatter provides imageUrlFormatter
        )
    }
}