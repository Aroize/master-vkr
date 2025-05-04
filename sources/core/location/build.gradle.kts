
import visify.tools.Plugins
import visify.tools.androidModule
import visify.tools.impl
import visify.tools.visifyKapt

androidModule(
    pkg = "care.visify.core.location",
    useCompose = true,
    plugins = listOf(
        Plugins.Hilt,
        Plugins.Kapt
    ),
    deps = {
        impl(libs.googleDagger.hiltAndroid)
        impl(projects.core.util)
        impl(libs.googleAndroidGms.playServicesLocation)
        visifyKapt(libs.googleDagger.hiltCompiler)

        impl(libs.androidxCompose.ui)
        impl(platform(libs.androidxCompose.bom))
    }
)