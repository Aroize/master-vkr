
import visify.tools.Plugins
import visify.tools.androidModule
import visify.tools.coreDesugaring
import visify.tools.impl
import visify.tools.visifyKapt

androidModule(
    pkg = "care.visify.core.api.http",
    useCompose = false,
    desugaringEnabled = true,
    plugins = listOf(
        Plugins.Hilt,
        Plugins.Kapt
    ),
    deps = {
        impl(projects.core.api.models)
        impl(projects.core.bus)
        impl(projects.core.pref)
        impl(projects.core.ktx)
        impl(projects.core.util)
        impl(projects.core.configuration)

        impl(libs.bundles.retrofit2)
        impl(libs.bundles.okHttp3)

        // todo(): remove flipper from release build
        impl(libs.facebook.flipper)
        impl(libs.facebook.soloader)
        impl(libs.facebook.flipperNetworkPlugin)
// releaseImplementation(libs.flipper.noop)

        impl(libs.googleDagger.hiltAndroid)
        visifyKapt(libs.googleDagger.hiltCompiler)

        coreDesugaring(libs.androidTools.desugarJdkLibs)
    }
)