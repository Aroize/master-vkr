package care.visify.business.core.init

import android.content.Context
import care.visify.business.BuildConfig
import care.visify.business.R
import care.visify.core.api.http.flipper.FlipperNetworkHolder
import care.visify.core.api.sse.engine.ServerConnectionEngine
import care.visify.core.navigator.impl.screen.SCREEN_MAP
import care.visify.feature.auth.impl.ui.AUTH_SCREEN
import care.visify.feature.dashboard.impl.screen.DASHBOARD_SCREEN
import care.visify.feature.debug.impl.navigation.DEBUG_SCREEN
import care.visify.feature.im.impl.core.ImEngine
import care.visify.feature.im.impl.ui.chat.selectnumber.screen.selectNumberResponseRegistry
import care.visify.feature.im.impl.ui.screen.BUSINESS_CHATS_SCREEN
import care.visify.feature.master.impl.screen.masterDetailedRegistry
import care.visify.feature.order.addphoto.impl.screen.addPhotoRegistry
import care.visify.feature.order.create.impl.screen.createOrderRegistry
import care.visify.feature.order.manage.impl.manageorder.screen.manageOrderRegistry
import care.visify.feature.order.manage.impl.visitchanges.screen.visitChangesRegistry
import care.visify.feature.order.suggestion.impl.screen.suggestionRegistry
import care.visify.feature.organisation.impl.screen.orgDetailedRegistry
import care.visify.feature.profile.business.screen.businessProfileRegistry
import care.visify.feature.salons.impl.about.screen.salonAboutRegistry
import care.visify.feature.salons.impl.detail.screen.SALON_DETAILED_SCREEN
import care.visify.feature.salons.impl.list.screen.SALONS_LIST_SCREEN
import care.visify.feature.salons.impl.manage.screen.salonManageRegistry
import care.visify.feature.settings.impl.SETTINGS_SCREEN
import care.visify.feature.timesheet.impl.ui.screen.TIMESHEET_SCREEN
import care.visify.ui.kit.overlay.navigator.globalRegistry
import care.visify.ui.kit.util.formatter.FormattersInitializer
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.crashreporter.CrashReporterPlugin
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class BusinessAppInitializer @Inject constructor(
    @ApplicationContext
    private val appContext: Context,
    private val imEngine: ImEngine,
    private val serverConnectionEngine: ServerConnectionEngine,
) {
    fun initializeApp() {
        FormattersInitializer.init(appContext)
        initializeNavigation()
        initFlipper(appContext)
        startServerConnection(serverConnectionEngine)
        startImEngine()
        initializeMaps(appContext)
    }

    private fun initializeNavigation() {
        SCREEN_MAP.apply {
            putAll(AUTH_SCREEN)
            putAll(DEBUG_SCREEN)
            putAll(DASHBOARD_SCREEN)
            putAll(SETTINGS_SCREEN)
            putAll(TIMESHEET_SCREEN)
            putAll(BUSINESS_CHATS_SCREEN)
            putAll(SALONS_LIST_SCREEN)
            putAll(SALON_DETAILED_SCREEN)
        }

        globalRegistry.apply {
            putAll(businessProfileRegistry)
            putAll(visitChangesRegistry)
            putAll(suggestionRegistry)
            putAll(masterDetailedRegistry)
            putAll(orgDetailedRegistry)
            putAll(selectNumberResponseRegistry)
            putAll(addPhotoRegistry)
            putAll(manageOrderRegistry)
            putAll(createOrderRegistry)
            putAll(salonAboutRegistry)
            putAll(salonManageRegistry)
        }
    }

    private fun initFlipper(context: Context) {
        if (BuildConfig.DEBUG.not()) return
        SoLoader.init(context, false)
        val flipper = AndroidFlipperClient.getInstance(context)
        flipper.addPlugin(CrashReporterPlugin.getInstance())
        flipper.addPlugin(SharedPreferencesFlipperPlugin(context))
        flipper.addPlugin(DatabasesFlipperPlugin(context))
        flipper.addPlugin(FlipperNetworkHolder.plugin)
        flipper.start()
    }

    private fun startServerConnection(engine: ServerConnectionEngine) {
        engine.start()
    }

    private fun startImEngine() {
        imEngine.start()
    }

    private fun initializeMaps(appContext: Context) {
        MapKitFactory.setApiKey(appContext.getString(R.string.yandex_maps_secret))
    }
}