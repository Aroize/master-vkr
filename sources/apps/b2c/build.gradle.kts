import dagger.hilt.android.plugin.HiltExtension
import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import visify.tools.Plugins
import visify.tools.Signing
import visify.tools.Variant
import visify.tools.androidApp
import visify.tools.androidTestImpl
import visify.tools.coreDesugaring
import visify.tools.debugImpl
import visify.tools.impl
import visify.tools.releaseImpl
import visify.tools.testImpl
import visify.tools.visifyKapt

androidApp(
    pkg = "care.visify.client",
    code = 1,
    version = "1.0",
    plugins = listOf(
        Plugins.Gms,
        Plugins.Crashlytics,
        Plugins.Hilt,
        Plugins.Kapt,
    ),
    useCompose = true,
    signings = listOf(
        Signing.ClientDebug, Signing.ClientRelease,
    ),
    variants = listOf(
        Variant.Debug, Variant.Release
    ),
    placeholders = mapOf(
        "VkExternalAuthRedirectScheme" to "vk51744557",
        "VkExternalAuthRedirectHost" to "vk.com"
    ),
    configure = {
        configure<KaptExtension> {
            correctErrorTypes = true
        }
        configure<HiltExtension> {
            enableAggregatingTask = false
        }
    },
    deps = {
        //region core
        impl(projects.core.api.models)
        impl(projects.core.api.http)
        impl(projects.core.api.sse)
        impl(projects.core.arch)
        impl(projects.core.bus)
        impl(projects.core.configuration)
        impl(projects.core.database)
        impl(projects.core.ktx)
        impl(projects.core.storage)
        impl(projects.core.repository)
        impl(projects.core.pref)
        impl(projects.core.location)
        impl(projects.core.time)
        impl(projects.core.image)
        impl(projects.core.notifications)
        impl(projects.core.navigator.impl)
        impl(projects.core.navigator.api)
        impl(projects.core.util)
        //endregion

        //region ui
        impl(projects.ui.kit)
        impl(projects.ui.icons)
        impl(projects.ui.models)
        //endregion

        //region feature
        impl(projects.feature.debug.api)
        debugImpl(projects.feature.debug.impl)
        releaseImpl(projects.feature.debug.noop)

        impl(projects.feature.camera)

        impl(projects.feature.common.models)
        impl(projects.feature.common.mappers)

        impl(projects.feature.auth.api)
        impl(projects.feature.auth.impl)

        impl(projects.feature.settings.api)
        impl(projects.feature.settings.impl)

        impl(projects.feature.user.api)
        impl(projects.feature.user.impl)

        impl(projects.feature.profile.api)
        impl(projects.feature.profile.client)

        impl(projects.feature.selectcity.api)
        impl(projects.feature.selectcity.impl)

        impl(projects.feature.order.common)
        impl(projects.feature.order.market.api)
        impl(projects.feature.order.market.impl)

        impl(projects.feature.order.visit.api)
        impl(projects.feature.order.visit.impl)

        impl(projects.feature.order.create.api)
        impl(projects.feature.order.create.impl)

        impl(projects.feature.order.edit.api)
        impl(projects.feature.order.edit.impl)

        impl(projects.feature.order.detailed.api)
        impl(projects.feature.order.detailed.impl)

        impl(projects.feature.order.detailedmarket.api)
        impl(projects.feature.order.detailedmarket.impl)

        impl(projects.feature.order.my.api)
        impl(projects.feature.order.my.impl)

        impl(projects.feature.order.suggestion.api)
        impl(projects.feature.order.suggestion.impl)

        impl(projects.feature.order.api)
        impl(projects.feature.order.domain)

        impl(projects.feature.categories.api)
        impl(projects.feature.categories.impl)

        impl(projects.feature.catalog.api)
        impl(projects.feature.catalog.impl)

        impl(projects.feature.calendar.api)
        impl(projects.feature.calendar.impl)

        impl(projects.feature.organisation.api)
        impl(projects.feature.organisation.impl)

        impl(projects.feature.organisations.api)
        impl(projects.feature.organisations.impl)

        impl(projects.feature.master.api)
        impl(projects.feature.master.impl)

        impl(projects.feature.masters.api)
        impl(projects.feature.masters.impl)

        impl(projects.feature.favourite.api)
        impl(projects.feature.favourite.impl)

        impl(projects.feature.favor.api)
        impl(projects.feature.favor.impl)

        impl(projects.feature.feedback.api)
        impl(projects.feature.feedback.impl)

        impl(projects.feature.filters.api)
        impl(projects.feature.filters.impl)

        impl(projects.feature.map.api)
        impl(projects.feature.map.impl)

        impl(projects.feature.im.api)
        impl(projects.feature.im.impl)

        impl(projects.feature.employee.api)
        impl(projects.feature.employee.impl)
        //endregion

        //region libs
        impl(libs.gitlabArturBosch.detektGradlePlugin)

        impl(libs.andoridx.hiltNavigationCompose)
        impl(libs.androidx.lifecycleViewmodelCompose)
        impl(libs.androidx.lifecycleRuntimeCompose)
        impl(libs.androidxCompose.toolingPreview)

        impl(libs.androidx.lifecycleRuntimeKtx)
        coreDesugaring(libs.androidTools.desugarJdkLibs)

        impl(libs.google.accompanistSystemUiController)

        impl(libs.androidx.lifecycleExtensions)
        impl(libs.androidx.coreKtx)
        impl(libs.androidx.appcompat)

        impl(libs.bundles.room)
        visifyKapt(libs.androidX.roomCompiler)

        impl(libs.googleAndroid.material)
        impl(libs.androidx.fragmentKtx)

        impl(libs.bundles.retrofit2)
        impl(libs.bundles.okHttp3)

        impl(libs.bundles.androidxCamera)

        impl(libs.google.accompanistPermissions)

        impl(libs.androidx.coreSplashscreen)

        impl(libs.googleDagger.hiltAndroid)
        visifyKapt(libs.googleDagger.hiltCompiler)

        impl(libs.wasabeef.glideTransformations)
        impl(libs.githubBumptechGlide.glide)
        impl(libs.githubBumptechGlide.okhttp3Integration.get()) {
            exclude(group = "glide-parent")
        }
        visifyKapt(libs.githubBumptechGlide.compiler)

        impl(libs.yandexAndroid.mapsMobile)

        impl(platform(libs.googleFirebase.firebaseBom))

        impl(libs.bundles.firebase)

        impl(libs.googleZxing.core)

        impl(libs.bundles.vk)

        impl(libs.facebook.flipper)
        impl(libs.facebook.soloader)
        impl(libs.facebook.flipperNetworkPlugin)
    // releaseImplementation(libs.flipper.noop) // todo(): change release to noop

        testImpl(libs.junit)
        androidTestImpl(libs.androidxTestExt.junit)
        androidTestImpl(libs.androidxTest.espressoCore)
        //endregion
    }

)
