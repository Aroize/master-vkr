package care.visify.ui.kit.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

object PhoneNumberOffsetTranslator : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int {
        return when {
            offset == 0 -> 0
            offset < 3 -> offset + 4
            offset <= 6 -> offset + 6
            offset <= 8 -> offset + 7
            offset < 11 -> offset + 8
            else -> 16
        }
    }

    override fun transformedToOriginal(offset: Int): Int {
        return when {
            offset <= 4 -> 0
            offset <= 7 -> offset - 4
            offset <= 12 -> offset - 6
            offset <= 15 -> offset - 7
            else -> offset - 8
        }
    }
}


object PhoneNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val source = text.take(10)
        val result = buildAnnotatedString {

            val firstChunk = source.take(3)
            val secondChunk = source.drop(3).take(3)
            val thirdChunk = source.drop(6).take(2)
            val lastChunk = source.drop(8).take(2)
            if (source.isNotEmpty()) {
                append("+7 (")
            }
            append(firstChunk)
            if (firstChunk.length == 3) {
                append(") ")
            }
            append(secondChunk)
            if (secondChunk.length == 3) {
                if (thirdChunk.isBlank()) append(' ') else append('-')
            }
            append(thirdChunk)
            if (thirdChunk.length == 2) {
                if (lastChunk.isBlank()) append(' ') else append('-')
            }
            append(lastChunk)
        }
        return TransformedText(result, PhoneNumberOffsetTranslator)
    }
}