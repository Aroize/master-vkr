package care.visify.client.core.navigation

import care.visify.client.R
import care.visify.core.navigator.api.VisifyRouter
import care.visify.core.navigator.api.bottomnav.BottomNavItem
import care.visify.core.navigator.api.bottomnav.BottomNavView
import care.visify.core.navigator.api.bottomnav.NavItemModel
import care.visify.core.navigator.impl.bottomnav.BottomNavStateHolder
import care.visify.core.navigator.impl.bottomnav.BottomNavViewController
import care.visify.feature.catalog.api.CatalogBottomNav
import care.visify.feature.catalog.api.CatalogScreen
import care.visify.feature.favourite.api.FavouritesBottomNav
import care.visify.feature.favourite.api.FavouritesScreen
import care.visify.feature.im.api.screen.ChatListBottomNav
import care.visify.feature.im.api.screen.ClientChatListScreen
import care.visify.feature.im.impl.client.counter.MessageCounterManager
import care.visify.feature.order.visit.api.VisitsBottomNav
import care.visify.feature.order.visit.api.VisitsScreen
import care.visify.feature.profile.api.ClientProfileScreen
import care.visify.feature.profile.api.ProfileScreenBottomNav
import care.visify.ui.icons.IconsR
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class ClientBottomNavViewController @Inject constructor(
    scope: CoroutineScope,
    controller: BottomNavStateHolder,
    private val router: VisifyRouter,
    private val counterManager: MessageCounterManager,
) : BottomNavViewController(scope, controller) {

    override fun initialItems(): List<NavItemModel> {
        return listOf(
            NavItemModel(
                iconRes = IconsR.ic_nav_main_24,
                textRes = R.string.bottom_nav_title_main
            ),
            NavItemModel(
                iconRes = IconsR.ic_heart_24,
                textRes = R.string.bottom_nav_title_fav
            ),
            NavItemModel(
                iconRes = IconsR.ic_nav_visit_24,
                textRes = R.string.visits_title
            ),
            NavItemModel(
                iconRes = IconsR.ic_nav_chat_24,
                textRes = R.string.chat_title
            ),
            NavItemModel(
                iconRes = IconsR.ic_nav_profile_24,
                textRes = R.string.bottom_nav_title_profile
            ),
        )
    }

    override fun toIntIdx(item: BottomNavItem): Int {
        return when (item) {
            CatalogBottomNav -> 0
            FavouritesBottomNav -> 1
            VisitsBottomNav -> 2
            ChatListBottomNav -> 3
            ProfileScreenBottomNav -> 4
            else -> throw IllegalArgumentException("Unknown bottom nav = $item")
        }
    }

    override fun onSelectedIdx(index: Int) {
        scope.launch {
            val screen = when (index) {
                0 -> CatalogScreen
                1 -> FavouritesScreen
                2 -> VisitsScreen
                3 -> ClientChatListScreen
                4 -> ClientProfileScreen
                else -> throw IllegalArgumentException("Unknown idx nav = $index")
            }
            router.newRootScreen(screen)
        }
    }

    override suspend fun subscribe() {
        counterManager.counter(forceUpdate = true)
            .collectLatest { state ->
                view.setBadgeValue(
                    idx = toIntIdx(ChatListBottomNav),
                    value = state.total
                )
            }
    }
}