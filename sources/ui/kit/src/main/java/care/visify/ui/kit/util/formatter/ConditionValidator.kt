package care.visify.ui.kit.util.formatter

import kotlin.math.pow

class ConditionValidator(
    private val lights: Int,
    private val mode: Mode = Mode.All,
    private val listener: Listener,
) {

    enum class Mode {
        Any, All
    }

    fun interface Listener {
        fun onConditionChanged(isValid: Boolean)
    }

    init {
        assert(lights in 1..31)
    }

    private val allMask = (POW_BASE.pow(lights) - 1).toInt()
    private var traffic = 0b0
        set(value) {
            field = value
            onValueChanged()
        }

    fun setLight(index: Int, isGreen: Boolean) {
        if (isGreen) setGreen(index) else setRed(index)
    }

    fun setGreen(index: Int) {
        if (index > lights) throw IllegalArgumentException("Index can't be greater then mask size")
        val mask = POW_BASE.pow(index).toInt()
        traffic = traffic or mask
    }

    fun setRed(index: Int) {
        if (index > lights) throw IllegalArgumentException("Index can't be greater then mask size")
        val mask = (allMask - POW_BASE.pow(index).toInt())
        traffic = traffic and mask
    }

    private fun onValueChanged() {
        val isValid = when (mode) {
            Mode.Any -> traffic.countOneBits() > 0
            Mode.All -> traffic == allMask
        }
        listener.onConditionChanged(isValid = isValid)
    }

    private companion object {
        const val POW_BASE = 2.0
    }
}