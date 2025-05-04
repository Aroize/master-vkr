package care.visify.core.navigator.impl.bottomnav

import care.visify.core.navigator.api.bottomnav.BottomNav
import care.visify.core.navigator.api.bottomnav.BottomNavItem
import care.visify.core.navigator.api.bottomnav.BottomNavView
import care.visify.core.navigator.api.bottomnav.NavItemModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BottomNavViewController(
    protected val scope: CoroutineScope,
    private val controller: BottomNavStateHolder
): BottomNavView.NavSelectionListener {

    protected lateinit var view: BottomNavView
    private var subscribeJob: Job? = null

    init {
        scope.launch {
            controller.flow.collect { nav ->
                handleNav(nav)
            }
        }
    }

    abstract fun toIntIdx(item: BottomNavItem): Int
    abstract fun initialItems(): List<NavItemModel>

    abstract suspend fun subscribe()

    fun bind(bottomNavView: BottomNavView) {
        view = bottomNavView.apply {
            setItems(initialItems())
            addListener(this@BottomNavViewController)
        }
        if (subscribeJob?.isActive == true) subscribeJob?.cancel()
        subscribeJob = scope.launch { subscribe() }
    }

    private fun handleNav(nav: BottomNav) {
        scope.launch(Dispatchers.Main.immediate) {
            when (nav) {
                BottomNav.Hide -> view.hide()
                BottomNav.Show -> view.show()
                is BottomNav.Stick -> {
                    view.show()
                    val idx = toIntIdx(nav.item)
                    view.setActive(idx)
                }
            }
        }
    }
}