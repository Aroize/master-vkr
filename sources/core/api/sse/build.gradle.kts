
import visify.tools.Plugins
import visify.tools.androidModule
import visify.tools.coreDesugaring
import visify.tools.impl
import visify.tools.visifyKapt

androidModule(
    pkg = "care.visify.core.api.sse",
    useCompose = false,
    desugaringEnabled = true,
    plugins = listOf(
        Plugins.Hilt,
        Plugins.Kapt
    ),
    deps = {

        impl(projects.core.api.models)
        impl(projects.core.api.http)
        impl(projects.core.bus)
        impl(projects.core.ktx)
        impl(projects.core.time)

        impl(libs.squareupOkhttp3.okhttp)
        impl(libs.bundles.retrofit2)

        impl(libs.googleDagger.hiltAndroid)
        visifyKapt(libs.googleDagger.hiltCompiler)

        impl(libs.bundles.room)
        visifyKapt(libs.androidX.roomCompiler)

        coreDesugaring(libs.androidTools.desugarJdkLibs)
    }
)