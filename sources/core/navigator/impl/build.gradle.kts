
import visify.tools.Plugins
import visify.tools.androidModule
import visify.tools.api
import visify.tools.impl
import visify.tools.visifyKapt

androidModule(
    pkg = "care.visify.core.navigator.impl",
    useCompose = true,
    plugins = listOf(
        Plugins.Hilt,
        Plugins.Kapt
    ),
    deps = {
        impl(libs.googleDagger.hiltAndroid)
        api(libs.githubTerrakok.cicerone)
        impl(projects.core.util)

        impl(projects.core.navigator.api)
        visifyKapt(libs.googleDagger.hiltCompiler)

        impl(libs.androidxCompose.ui)
        impl(platform(libs.androidxCompose.bom))
    }
)