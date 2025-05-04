
import visify.tools.Plugins
import visify.tools.androidModule
import visify.tools.impl
import visify.tools.visifyKapt

androidModule(
    pkg = "care.visify.core.notifications",
    useCompose = false,
    plugins = listOf(
        Plugins.Hilt,
        Plugins.Kapt
    ),
    deps = {
        impl(projects.core.ktx)
        impl(projects.core.bus)
        impl(projects.core.api.models)
        impl(projects.core.api.http)
        impl(projects.core.api.sse)
        impl(projects.core.util)

        impl(projects.core.repository)

        impl(projects.ui.icons)

        impl(projects.feature.im.api)

        impl(libs.bundles.retrofit2)
        impl(platform(libs.googleFirebase.firebaseBom))
        impl(libs.googleFirebase.firebaseMessagingKtx)

        impl(libs.googleDagger.hiltAndroid)
        visifyKapt(libs.googleDagger.hiltCompiler)
    }
)