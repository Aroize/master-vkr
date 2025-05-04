package care.visify.client.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import care.visify.client.R
import care.visify.client.core.navigation.ClientBottomNavViewController
import care.visify.core.bus.EventBus
import care.visify.core.location.helper.LocationHelperFactory
import care.visify.core.location.helper.LocationHelperHolder
import care.visify.core.navigator.api.VisifyRouter
import care.visify.core.navigator.impl.UsableAppNavigator
import care.visify.core.notifications.intent.IntentHandler
import care.visify.core.notifications.push.base.PushHelper
import care.visify.core.util.atLeastTiramisu
import care.visify.feature.catalog.api.CatalogScreen
import care.visify.feature.debug.api.DevTools
import care.visify.feature.selectcity.api.GetLocationScreen
import care.visify.feature.selectcity.api.usecase.GetSelectedCityUseCase
import care.visify.ui.kit.components.nav.bottom.BottomNavigationBar
import care.visify.ui.kit.fragment.VisifyActivity
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : VisifyActivity() {

    private lateinit var bottomNavigationBar: BottomNavigationBar

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { }

    private val locationHelper = LocationHelperFactory.create(this, this)

    @Inject
    lateinit var pushHelper: PushHelper

    @Inject
    lateinit var bus: EventBus

    @Inject
    override lateinit var router: VisifyRouter

    @Inject
    override lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var getSelectedCityUseCase: GetSelectedCityUseCase

    @Inject
    lateinit var bottomNavController: ClientBottomNavViewController

    @Inject
    lateinit var devTools: DevTools

    @Inject
    lateinit var intentHandler: IntentHandler

    override val navigator: Navigator by lazy { UsableAppNavigator(this, R.id.container) }

    @SuppressLint("InlinedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        LocationHelperHolder.setup(locationHelper)

        super.onCreate(savedInstanceState)

        atLeastTiramisu(
            action = { permissionLauncher.launch(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS)) },
            fallback = { }
        )

        devTools.init(this)

        MapKitFactory.initialize(this)

        setContentView(R.layout.activity_main)
        bottomNavigationBar = findViewById(R.id.bottom_nav_bar)
        initBottomNav()

        checkInitialData()
    }

    override fun onResume() {
        super.onResume()
        pushHelper.start(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        LocationHelperHolder.clear()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intentHandler.handle(intent)
    }

    private fun initBottomNav() {
        bottomNavController.bind(bottomNavigationBar)
    }

    private fun checkInitialData() {
        lifecycleScope.launch {

            val selectedCityId = getSelectedCityUseCase()
            if (selectedCityId < 0) {
                hideBottomNav()
                router.newRootScreen(GetLocationScreen)
            } else {
                if (intentHandler.handleInitial(intent)) {
                    return@launch
                } else {
                    router.replaceScreen(CatalogScreen)
                }
            }
        }
        return
    }

    private fun hideBottomNav() {
        bottomNavigationBar.isVisible = false
    }
}