package care.visify.core.navigator.impl.bottomnav

import care.visify.core.navigator.api.bottomnav.BottomNav
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class BottomNavStateHolder @Inject constructor() {

    private val mutableFlow = MutableStateFlow<BottomNav>(BottomNav.Hide)

    val flow: Flow<BottomNav>
        get() = mutableFlow

    suspend fun reduce(bottomNav: BottomNav) {
        mutableFlow.emit(bottomNav)
    }
}