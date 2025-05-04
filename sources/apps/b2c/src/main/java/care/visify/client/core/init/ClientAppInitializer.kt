package care.visify.client.core.init

import android.app.Application
import android.content.Context
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ProcessLifecycleOwner
import care.visify.client.BuildConfig
import care.visify.client.R
import care.visify.client.core.design.DESIGN_SYSTEM_SCREEN
import care.visify.core.api.http.connectivity.NetworkManager
import care.visify.core.api.http.flipper.FlipperNetworkHolder
import care.visify.core.api.sse.engine.ServerConnectionEngine
import care.visify.core.navigator.impl.screen.SCREEN_MAP
import care.visify.core.notifications.NotificationHelper
import care.visify.core.util.AppContextHolder
import care.visify.core.util.AppLifecycleHandler
import care.visify.feature.auth.impl.ui.AUTH_SCREEN
import care.visify.feature.catalog.impl.screen.CATALOG_SCREEN
import care.visify.feature.categories.impl.CATEGORIES_SCREEN
import care.visify.feature.debug.impl.navigation.DEBUG_SCREEN
import care.visify.feature.favourite.impl.screen.FAVOURITES_SCREEN
import care.visify.feature.feedback.impl.screen.feedbackRegistry
import care.visify.feature.filters.impl.screen.filtersRegistry
import care.visify.feature.im.impl.core.ImEngine
import care.visify.feature.im.impl.ui.chat.selectnumber.screen.selectNumberResponseRegistry
import care.visify.feature.im.impl.ui.screen.CLIENT_CHATS_SCREEN
import care.visify.feature.map.impl.screen.MAP_SCREEN
import care.visify.feature.master.impl.screen.masterDetailedRegistry
import care.visify.feature.masters.impl.screen.MASTERS_SCREEN
import care.visify.feature.order.create.impl.screen.createOrderRegistry
import care.visify.feature.order.detailed.impl.screen.detailedOrderRegistry
import care.visify.feature.order.detailedmarket.impl.screen.detailedMarketRegistry
import care.visify.feature.order.edit.impl.screen.editOrderRegistry
import care.visify.feature.order.market.impl.MARKET_ORDER_SCREEN
import care.visify.feature.order.my.impl.screen.MY_ORDERS_SCREEN
import care.visify.feature.order.suggestion.impl.screen.suggestionRegistry
import care.visify.feature.order.visit.impl.screen.VISITS_SCREEN
import care.visify.feature.organisation.impl.screen.orgDetailedRegistry
import care.visify.feature.organisations.impl.screen.ORGANISATIONS_SCREEN
import care.visify.feature.profile.client.screen.CLIENT_PROFILE_SCREEN
import care.visify.feature.selectcity.impl.GET_CITY_SCREEN
import care.visify.feature.settings.impl.SETTINGS_SCREEN
import care.visify.ui.icons.MipmapR
import care.visify.ui.kit.overlay.navigator.globalRegistry
import care.visify.ui.kit.util.formatter.FormattersInitializer
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.crashreporter.CrashReporterPlugin
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader
import com.vk.api.sdk.VK
import com.vk.auth.main.VkClientUiInfo
import com.vk.auth.main.VkSilentTokenExchanger
import com.vk.superapp.SuperappKit
import com.vk.superapp.SuperappKitConfig
import com.vk.superapp.core.SuperappConfig
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class ClientAppInitializer @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val notificationHelper: NotificationHelper,
    private val silentTokenExchanger: VkSilentTokenExchanger,
    private val networkManager: NetworkManager,
    private val serverConnectionEngine: ServerConnectionEngine,
    private val imEngine: ImEngine
) {
    fun initializeApplication() {
        FormattersInitializer.init(appContext)
        initializeAppContext(appContext)
        initializeNavigation()
        initializeExternalAuth(appContext, silentTokenExchanger)
        initializeMaps(appContext)
        initNotifications(notificationHelper)
        initFlipper(appContext)
        startNetworkManager(networkManager)
        startServerConnection(serverConnectionEngine)
        startImEngine()
    }

    private fun initializeAppContext(appContext: Context) {
        AppContextHolder.init(appContext)
    }

    private fun initializeExternalAuth(
        appContext: Context,
        silentTokenExchanger: VkSilentTokenExchanger,
    ) {
        val appName = appContext.getString(R.string.app_name)
        val clientSecret = appContext.getString(R.string.vk_client_secret)
        val icon = AppCompatResources.getDrawable(appContext, MipmapR.ic_launcher_round_client)!!
        val appInfo = SuperappConfig.AppInfo(
            appName = appName,
            appId = VK.getAppId(appContext).toString(),
            appVersion = BuildConfig.VERSION_NAME
        )
        val config = SuperappKitConfig.Builder(appContext as Application)
            .setAuthModelData(clientSecret)
            .setAuthUiManagerData(VkClientUiInfo(icon, appName))
            .setLegalInfoLinks(
                serviceUserAgreement = "https://id.vk.com/terms",
                servicePrivacyPolicy = "https://id.vk.com/privacy"
            )
            .setApplicationInfo(appInfo)
            .setSilentTokenExchanger(silentTokenExchanger)
            .build()
        SuperappKit.init(config)
    }

    private fun initializeMaps(appContext: Context) {
        MapKitFactory.setApiKey(appContext.getString(R.string.yandex_maps_secret))
    }

    private fun initNotifications(
        notificationHelper: NotificationHelper,
    ) {
        notificationHelper.init()
        ProcessLifecycleOwner.get().lifecycle.addObserver(AppLifecycleHandler)
    }

    private fun initializeNavigation() {
        SCREEN_MAP.apply {
            putAll(DESIGN_SYSTEM_SCREEN)
            putAll(GET_CITY_SCREEN)
            putAll(AUTH_SCREEN)
            putAll(CATALOG_SCREEN)
            putAll(MARKET_ORDER_SCREEN)
            putAll(ORGANISATIONS_SCREEN)
            putAll(CATEGORIES_SCREEN)
            putAll(FAVOURITES_SCREEN)
            putAll(VISITS_SCREEN)
            putAll(MASTERS_SCREEN)
            putAll(CLIENT_CHATS_SCREEN)
            putAll(CLIENT_PROFILE_SCREEN)
            putAll(SETTINGS_SCREEN)
            putAll(MY_ORDERS_SCREEN)
            putAll(DEBUG_SCREEN)
            putAll(MAP_SCREEN)
        }

        globalRegistry.apply {
            putAll(orgDetailedRegistry)
            putAll(masterDetailedRegistry)
            putAll(feedbackRegistry)
            putAll(filtersRegistry)
            putAll(createOrderRegistry)
            putAll(editOrderRegistry)
            putAll(detailedOrderRegistry)
            putAll(detailedMarketRegistry)
            putAll(suggestionRegistry)
            putAll(selectNumberResponseRegistry)
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

    private fun startNetworkManager(manager: NetworkManager) {
        manager.start()
    }

    private fun startServerConnection(engine: ServerConnectionEngine) {
        engine.start()
    }

    private fun startImEngine() {
        imEngine.start()
    }
}