package care.visify.ui.kit.util.formatter

import androidx.compose.ui.text.buildAnnotatedString

object PhoneNumberFormatter {
    //from 79990001122 to +7 (999) 000-11-22
    fun transform(phone : String) : String {
        val source = phone.take(11)
        val result = buildAnnotatedString {

            val firstChunk = source.take(1)
            val secondChunk = source.drop(1).take(3)
            val thirdChunk = source.drop(4).take(3)
            val fourthChunk = source.drop(7).take(2)
            val lastChunk = source.drop(9).take(2)
            if (source.isNotEmpty()) {
                append("+")
                append(firstChunk)
            }
            if (secondChunk.length == 3) {
                append(" (")
            }
            append(secondChunk)
            if (secondChunk.length == 3) {
                append(") ")
            }
            append(thirdChunk)
            if (thirdChunk.length == 3) {
                if (fourthChunk.isBlank()) append(' ') else append('-')
            }
            append(fourthChunk)
            if (fourthChunk.length == 2) {
                if (lastChunk.isBlank()) append(' ') else append('-')
            }
            append(lastChunk)
        }
        return result.text
    }
}