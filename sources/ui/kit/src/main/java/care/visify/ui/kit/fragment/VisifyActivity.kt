package care.visify.ui.kit.fragment

import android.os.Bundle
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import care.visify.core.navigator.api.VisifyRouter
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import kotlinx.coroutines.launch

abstract class VisifyActivity : AppCompatActivity() {

    abstract var router: VisifyRouter
    abstract var navigatorHolder: NavigatorHolder
    abstract val navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        onBackPressedDispatcher.addCallback(this) {
            lifecycleScope.launch { router.backTo(null) }
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}