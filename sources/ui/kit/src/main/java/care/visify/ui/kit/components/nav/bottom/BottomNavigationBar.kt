package care.visify.ui.kit.components.nav.bottom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.view.isVisible
import care.visify.core.navigator.api.bottomnav.BottomNavView
import care.visify.core.navigator.api.bottomnav.NavItemModel
import care.visify.ui.kit.R


class BottomNavigationBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) :
    FrameLayout(context, attrs, defStyleAttr, defStyleRes),
    View.OnClickListener,
    BottomNavView {

    init {
        addView(inflate(context, R.layout.bottom_nav_bar, null))
    }

    private val container = findViewById<ViewGroup>(R.id.bottom_nav_container)
    private val listeners = mutableListOf<BottomNavView.NavSelectionListener>()
    private var icons: List<BottomNavItemView> = emptyList()

    private var selectedItemIdx = 0

    override fun onClick(v: View) {
        val clickedIdx = icons.indexOf(v)
        if (clickedIdx < 0) throw IllegalArgumentException("Can't find $v in bottom nav items")
        if (clickedIdx == selectedItemIdx) return
        selectedItemIdx = clickedIdx
        listeners.forEach { it.onSelectedIdx(selectedItemIdx) }
        invalidateBottomNav()
    }

    override fun setItems(items: List<NavItemModel>) {
        container.removeAllViews()
        icons = items.map { item ->
            BottomNavItemView(context).apply {
                setImage(item.iconRes)
                setText(item.textRes)
            }
        }
        icons.forEach { item ->
            container.addView(item, createItemLayoutParams())
            item.setOnClickListener(this)
        }
    }

    override fun setActive(idx: Int) {
        selectedItemIdx = idx
        invalidateBottomNav()
    }

    override fun setBadgeValue(idx: Int, value: Int) {
        val item = icons[idx]
        item.setBadge(value)
    }

    override fun hide() { isVisible = false }

    override fun show() { isVisible = true }

    override fun addListener(listener: BottomNavView.NavSelectionListener) {
        listeners += listener
    }

    private fun invalidateBottomNav() {
        icons.forEachIndexed { index, icon ->
            val selected = index == selectedItemIdx
            icon.isNavItemSelected(selected)
        }
    }

    private fun createItemLayoutParams(): ViewGroup.LayoutParams {
        return LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT)
            .apply { weight = 1f }
    }
}