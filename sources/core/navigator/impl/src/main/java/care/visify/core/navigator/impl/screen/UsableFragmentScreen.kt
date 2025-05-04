package care.visify.core.navigator.impl.screen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import care.visify.core.navigator.api.VisifyScreen
import com.github.terrakok.cicerone.androidx.Creator
import com.github.terrakok.cicerone.androidx.FragmentScreen

internal interface UsableFragmentScreen : FragmentScreen {

    val clientScreen: VisifyScreen

    companion object {
        operator fun invoke(
            screen: VisifyScreen,
            key: String? = null,
            clearContainer: Boolean = true,
            fragmentCreator: Creator<FragmentFactory, Fragment>,
        ) = object : UsableFragmentScreen {
            override val clientScreen: VisifyScreen = screen
            override val screenKey = key ?: screen::class.java.name
            override val clearContainer = clearContainer
            override fun createFragment(factory: FragmentFactory) = fragmentCreator.create(factory)
        }
    }
}