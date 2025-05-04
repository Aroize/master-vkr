package care.visify.ui.kit.containers.sheet.sorting

import androidx.annotation.StringRes


data class Sorting(
    val type: Type,
    val selected: Boolean,
    @StringRes val name: Int
) {
    enum class Type { NEW, TOP, LOW, WEARING }
}