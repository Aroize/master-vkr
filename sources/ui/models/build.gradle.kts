import visify.tools.Plugins
import visify.tools.androidModule
import visify.tools.coreDesugaring
import visify.tools.impl
import visify.tools.visifyKapt

androidModule(
    pkg = "care.visify.ui.models",
    useCompose = false,
    desugaringEnabled = true,
    plugins = listOf(
        Plugins.Hilt,
        Plugins.Kapt
    ),
    deps = {
        impl(projects.core.image) //todo ui зависит от core
        impl(projects.core.util)

        impl(libs.googleDagger.hiltAndroid)
        visifyKapt(libs.googleDagger.hiltCompiler)
        impl(libs.jetbrains.kotlinxCollectionsImmutable)

        coreDesugaring(libs.androidTools.desugarJdkLibs)
    }
)