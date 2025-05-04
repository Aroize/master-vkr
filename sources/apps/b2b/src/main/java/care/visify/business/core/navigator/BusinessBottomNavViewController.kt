package care.visify.business.core.navigator

import care.visify.business.R
import care.visify.core.navigator.api.VisifyRouter
import care.visify.core.navigator.api.bottomnav.BottomNavItem
import care.visify.core.navigator.api.bottomnav.NavItemModel
import care.visify.core.navigator.impl.bottomnav.BottomNavStateHolder
import care.visify.core.navigator.impl.bottomnav.BottomNavViewController
import care.visify.feature.dashboard.api.screen.DashboardBottomNav
import care.visify.feature.dashboard.api.screen.DashboardScreen
import care.visify.feature.im.api.screen.BusinessChatListBottomNav
import care.visify.feature.im.api.screen.BusinessChatListScreen
import care.visify.feature.salons.api.screen.SalonsListBottomNav
import care.visify.feature.salons.api.screen.SalonsListScreen
import care.visify.feature.timesheet.api.TimesheetBottomNav
import care.visify.feature.timesheet.api.TimesheetScreen
import care.visify.ui.icons.IconsR
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class BusinessBottomNavViewController @Inject constructor(
    scope: CoroutineScope,
    controller: BottomNavStateHolder,
    private val router: VisifyRouter,
) : BottomNavViewController(scope, controller) {

    override fun initialItems(): List<NavItemModel> =
        listOf(
            NavItemModel(
                iconRes = IconsR.ic_nav_dashboard_24,
                textRes = R.string.bottom_nav_title_dashboard
            ),
            NavItemModel(
                iconRes = IconsR.ic_nav_visit_24,
                textRes = R.string.bottom_nav_title_visits
            ),
            NavItemModel(
                iconRes = IconsR.ic_nav_chat_24,
                textRes = R.string.bottom_nav_title_chats
            ),
            NavItemModel(
                iconRes = IconsR.ic_nav_salon_new_24,
                textRes = R.string.bottom_nav_title_salons
            ),
        )


    override fun toIntIdx(item: BottomNavItem): Int =
        when (item) {
            DashboardBottomNav -> 0
            TimesheetBottomNav -> 1
            BusinessChatListBottomNav -> 2
            SalonsListBottomNav -> 3
            else -> throw IllegalArgumentException("Unknown bottom nav = $item")
        }

    override fun onSelectedIdx(index: Int) {
        scope.launch {
            val screen = when (index) {
                0 -> DashboardScreen
                1 -> TimesheetScreen
                2 -> BusinessChatListScreen
                3 -> SalonsListScreen
                else -> throw IllegalArgumentException("Unknown idx nav = $index")
            }
            router.newRootScreen(screen)
        }
    }

    override suspend fun subscribe() = Unit
}