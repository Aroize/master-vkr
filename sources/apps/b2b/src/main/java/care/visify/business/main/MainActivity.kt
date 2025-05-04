package care.visify.business.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import care.visify.business.R
import care.visify.business.core.navigator.BusinessBottomNavViewController
import care.visify.business.main.state.BusinessIntent
import care.visify.core.location.helper.LocationHelperFactory
import care.visify.core.location.helper.LocationHelperHolder
import care.visify.core.navigator.api.VisifyRouter
import care.visify.core.navigator.impl.UsableAppNavigator
import care.visify.core.notifications.push.base.PushHelper
import care.visify.core.util.atLeastTiramisu
import care.visify.ui.kit.components.nav.bottom.BottomNavigationBar
import care.visify.ui.kit.fragment.VisifyActivity
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : VisifyActivity() {

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { }

    private lateinit var bottomNavigationBar: BottomNavigationBar

    private val locationHelper = LocationHelperFactory.create(this, this)

    @Inject
    override lateinit var router: VisifyRouter

    @Inject
    override lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var bottomNavController: BusinessBottomNavViewController

    @Inject
    lateinit var pushHelper: PushHelper
    override val navigator: Navigator by lazy { UsableAppNavigator(this, R.id.container) }

    private val viewModel by viewModels<BusinessViewModel>()

    @SuppressLint("InlinedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        LocationHelperHolder.setup(locationHelper)
        super.onCreate(savedInstanceState)

        atLeastTiramisu(
            action = { permissionLauncher.launch(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS)) },
            fallback = { }
        )

        viewModel.handleIntent(BusinessIntent.InitDevTools(this))

        setContentView(R.layout.activity_main)

        initBottomNav()

        subscribe()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        viewModel.handleIntent(BusinessIntent.HandleAndroidOnNewIntent(intent))
    }

    override fun onResume() {
        super.onResume()
        pushHelper.start(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        LocationHelperHolder.clear()
    }

    private fun initBottomNav() {
        bottomNavigationBar = findViewById(R.id.bottom_nav_bar)
        bottomNavController.bind(bottomNavigationBar)
    }

    private fun subscribe() {
        lifecycleScope.launch {
            viewModel.effect.collect { _ ->
                // todo()
            }
        }
    }
}