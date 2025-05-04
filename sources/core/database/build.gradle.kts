import visify.tools.Plugins
import visify.tools.androidModule
import visify.tools.impl
import visify.tools.visifyKapt

androidModule(
    pkg = "care.visify.core.database",
    useCompose = false,
    plugins = listOf(
        Plugins.Hilt,
        Plugins.Kapt
    ),
    deps = {
        impl(projects.core.util)

        impl(libs.bundles.room)
        visifyKapt(libs.androidX.roomCompiler)
        impl(libs.googleDagger.hiltAndroid)
        visifyKapt(libs.googleDagger.hiltCompiler)
    }
)