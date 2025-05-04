package care.visify.ui.kit.fragment

import androidx.compose.runtime.ProvidedValue

interface CompositionProvider {
    fun provideCompositions(): Array<ProvidedValue<*>>
}