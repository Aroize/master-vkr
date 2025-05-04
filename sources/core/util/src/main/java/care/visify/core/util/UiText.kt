package care.visify.core.util

import androidx.annotation.StringRes

sealed class UiText {
    data class DynamicString(val value: String) : UiText()
    class ResString(
        @StringRes val res: Int,
        vararg val args: Any,
    ) : UiText()

    class ResArray(
        @StringRes val res: Int,
        val index: Int
    )

    fun isEmpty(): Boolean {
        return when (this) {
            is DynamicString -> value.isEmpty()
            is ResString -> false
        }
    }
}
