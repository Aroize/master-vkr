package care.visify.core.storage.processors

import care.visify.core.storage.BitmapModifier
import care.visify.core.storage.ImageModifier

class BitmapModifierHolder {
    private val processors = listOf(ScaleBitmapModifier(), SquareCropBitmapModifier())

    fun get(type: ImageModifier): BitmapModifier {
        return processors.find { it.type == type }
            ?: throw IllegalArgumentException("No processor for type = $type")
    }
}