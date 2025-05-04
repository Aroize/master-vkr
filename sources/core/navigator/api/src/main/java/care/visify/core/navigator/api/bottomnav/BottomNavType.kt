package care.visify.core.navigator.api.bottomnav

interface BottomNavItem

sealed interface BottomNav {
    data class Stick(val item: BottomNavItem): BottomNav
    data object Show: BottomNav
    data object Hide: BottomNav
}