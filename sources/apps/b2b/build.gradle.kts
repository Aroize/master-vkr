import dagger.hilt.android.plugin.HiltExtension
import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import visify.tools.Plugins
import visify.tools.Variant
import visify.tools.androidApp
import visify.tools.coreDesugaring
import visify.tools.debugImpl
import visify.tools.impl
import visify.tools.releaseImpl
import visify.tools.visifyKapt

androidApp(
    pkg = "care.visify.business",
    code = 1,
    version = "1.0",
    plugins = listOf(
        Plugins.Hilt,
        Plugins.Kapt,
        Plugins.Gms,
        Plugins.Crashlytics,
    ),
    useCompose = true,
    signings = emptyList(),
    variants = listOf(
        Variant.Debug, Variant.Release
    ),
    configure = {
        configure<KaptExtension> {
            correctErrorTypes = true
        }
        configure<HiltExtension> {
            enableAggregatingTask = false
        }
    },
    //todo BUSINESS VALUES
    placeholders = mapOf(
        "VkExternalAuthRedirectScheme" to "vk51744557",
        "VkExternalAuthRedirectHost" to "vk.com"
    ),
    deps = {

        // region core
        impl(projects.core.ktx)
        impl(projects.core.arch)
        impl(projects.core.bus)
        impl(projects.core.configuration)
        impl(projects.core.database)
        impl(projects.core.notifications)
        impl(projects.core.pref)
        impl(projects.core.storage)
        impl(projects.core.time)
        impl(projects.core.location)
        impl(projects.core.navigator.api)
        impl(projects.core.navigator.impl)
        impl(projects.core.api.http)
        impl(projects.core.api.sse)
        impl(projects.core.util)
        impl(projects.core.api.models)
        impl(projects.core.image)
        impl(projects.core.time)
        // endregion

        // region ui
        impl(projects.ui.icons)
        impl(projects.ui.kit)
        impl(projects.ui.models)

        // endregion

        // region feature
        impl(projects.feature.common.mappers)
        impl(projects.feature.common.models)

        impl(projects.feature.auth.api)
        impl(projects.feature.auth.impl)

        impl(projects.feature.dashboard.api)
        impl(projects.feature.dashboard.impl)

        impl(projects.feature.categories.api)
        impl(projects.feature.categories.impl)

        impl(projects.feature.favor.api)
        impl(projects.feature.favor.impl)

        impl(projects.feature.favourite.api)
        impl(projects.feature.favourite.impl)

        impl(projects.feature.organisation.api)
        impl(projects.feature.organisation.impl)

        impl(projects.feature.master.api)
        impl(projects.feature.master.impl)

        impl(projects.feature.feedback.api)
        impl(projects.feature.feedback.impl)

        impl(projects.feature.order.common)
        impl(projects.feature.order.api)
        impl(projects.feature.order.domain)

        impl(projects.feature.order.manage.api)
        impl(projects.feature.order.manage.impl)

        impl(projects.feature.order.suggestion.impl)
        impl(projects.feature.order.suggestion.api)

        impl(projects.feature.profile.api)
        impl(projects.feature.profile.business)

        impl(projects.feature.settings.api)
        impl(projects.feature.settings.impl)

        impl(projects.feature.user.api)
        impl(projects.feature.user.impl)

        debugImpl(projects.feature.debug.impl)
        impl(projects.feature.debug.api)
        releaseImpl(projects.feature.debug.noop)

        impl(projects.feature.camera)

        impl(projects.feature.timesheet.api)
        impl(projects.feature.timesheet.impl)

        impl(projects.feature.im.api)
        impl(projects.feature.im.impl)

        impl(projects.feature.employee.api)
        impl(projects.feature.employee.impl)

        impl(projects.feature.calendar.api)
        impl(projects.feature.calendar.impl)

        impl(projects.feature.order.addphoto.api)
        impl(projects.feature.order.addphoto.impl)

        impl(projects.feature.order.create.api)
        impl(projects.feature.order.create.impl)

        impl(projects.feature.salons.api)
        impl(projects.feature.salons.impl)
        // endregion

        // region libs
        impl(libs.androidx.coreKtx)
        impl(libs.androidx.appcompat)
        impl(libs.androidx.lifecycleExtensions)
        impl(libs.androidx.coreSplashscreen)

        coreDesugaring(libs.androidTools.desugarJdkLibs)

        impl(libs.bundles.vk)

        impl(libs.googleDagger.hiltAndroid)
        visifyKapt(libs.googleDagger.hiltCompiler)

        impl(libs.wasabeef.glideTransformations)
        impl(libs.githubBumptechGlide.glide)
        impl(libs.githubBumptechGlide.okhttp3Integration.get()) {
            exclude(group = "glide-parent")
        }
        visifyKapt(libs.githubBumptechGlide.compiler)

        impl(libs.bundles.room)
        visifyKapt(libs.androidX.roomCompiler)

        impl(libs.squareupRetrofit2.retrofit)
        impl(libs.squareupRetrofit2.converterGson)

        impl(platform(libs.googleFirebase.firebaseBom))
        impl(libs.bundles.firebase)

        impl(libs.facebook.flipper)
        impl(libs.facebook.soloader)
        impl(libs.facebook.flipperNetworkPlugin)

        impl(libs.yandexAndroid.mapsMobile)
        // endregion libs
    }
)