
import visify.tools.Plugins
import visify.tools.androidModule
import visify.tools.coreDesugaring
import visify.tools.impl
import visify.tools.visifyKapt

androidModule(
    pkg = "care.visify.core.time",
    useCompose = false,
    desugaringEnabled = true,
    plugins = listOf(
        Plugins.Hilt,
        Plugins.Kapt
    ),
    deps = {
        impl(libs.googleDagger.hiltAndroid)
        visifyKapt(libs.googleDagger.hiltCompiler)
        coreDesugaring(libs.androidTools.desugarJdkLibs)
    }
)