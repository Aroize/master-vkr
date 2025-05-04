package care.visify.core.navigator.api.bottomnav

interface BottomNavView {

    fun interface NavSelectionListener {
        fun onSelectedIdx(index: Int)
    }

    fun setActive(idx: Int)
    fun setItems(items: List<NavItemModel>)
    fun setBadgeValue(idx: Int, value: Int)
    fun show()
    fun hide()
    fun addListener(listener: NavSelectionListener)
}