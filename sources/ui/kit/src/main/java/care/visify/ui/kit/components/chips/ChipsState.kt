package care.visify.ui.kit.components.chips

enum class ChipsState(val value: Int) {
    ActiveWithRemove(0),
    Active(1),
    Inactive(2),
    Unspecified(Int.MAX_VALUE);

    val isActive: Boolean
        get() = this == ActiveWithRemove || this == Active
}

fun Boolean.chips() = if (this) ChipsState.ActiveWithRemove else ChipsState.Inactive