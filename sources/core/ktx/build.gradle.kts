
import visify.tools.Plugins
import visify.tools.androidModule
import visify.tools.impl
import visify.tools.visifyKapt

androidModule(
    pkg = "care.visify.core.ktx",
    useCompose = false,
    plugins = listOf(
        Plugins.Hilt,
        Plugins.Kapt
    ),
    deps = {
        impl(libs.googleDagger.hiltAndroid)
        visifyKapt(libs.googleDagger.hiltCompiler)
    }
)