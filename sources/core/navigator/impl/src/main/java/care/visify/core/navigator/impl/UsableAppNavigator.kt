package care.visify.core.navigator.impl

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import care.visify.core.navigator.impl.screen.UsableFragmentScreen
import care.visify.core.util.multiLet
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen

class UsableAppNavigator(
    activity: FragmentActivity,
    containerId: Int
) : AppNavigator(activity, containerId) {
    override fun setupFragmentTransaction(
        screen: FragmentScreen,
        fragmentTransaction: FragmentTransaction,
        currentFragment: Fragment?,
        nextFragment: Fragment,
    ) {
        if (screen !is UsableFragmentScreen) return
        multiLet(
            screen.clientScreen.animEnter,
            screen.clientScreen.animOut,
            screen.clientScreen.popAnimEnter,
            screen.clientScreen.popAnimOut
        ) { enter, exit, popEnter, popOut ->
            fragmentTransaction.setCustomAnimations(enter, exit, popEnter, popOut)
        }
    }
}