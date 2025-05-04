
import visify.tools.Plugins
import visify.tools.androidModule
import visify.tools.impl
import visify.tools.visifyKapt

androidModule(
    pkg = "care.visify.core.image",
    useCompose = false,
    plugins = listOf(
        Plugins.Hilt,
        Plugins.Kapt
    ),
    deps = {

        impl(projects.core.api.models)
        impl(projects.core.api.http)
        impl(projects.core.ktx)
        impl(projects.core.storage)
        impl(projects.core.repository)
        impl(projects.core.util)

        impl(libs.squareupRetrofit2.retrofit)

        impl(libs.googleDagger.hiltAndroid)
        visifyKapt(libs.googleDagger.hiltCompiler)

        impl(libs.githubBumptechGlide.glide)
    }
)