import visify.tools.Plugins
import visify.tools.androidModule
import visify.tools.api
import visify.tools.coreDesugaring
import visify.tools.impl
import visify.tools.visifyKapt

androidModule(
    pkg = "care.visify.ui.kit",
    desugaringEnabled = true,
    useCompose = true,
    plugins = listOf(
        Plugins.Kapt,
        Plugins.Hilt
    ),
    deps = {
        impl(projects.ui.icons)
        impl(projects.ui.models)
        impl(projects.core.image)
        impl(projects.core.navigator.api)
        impl(projects.core.util)
        impl(projects.core.ktx)

        api(libs.githubBumptechGlide.compose)
        api(libs.jetbrains.kotlinxCollectionsImmutable)

        impl(libs.googleAndroid.material)
        impl(libs.githubNikartm.imageSupport)
        impl(libs.androidx.fragmentKtx)

        impl(libs.yandexAndroid.mapsMobile)

        api(libs.bundles.composeUi)
        api(libs.bundles.composeExt)

        coreDesugaring(libs.androidTools.desugarJdkLibs)

        impl(libs.googleZxing.core)
        impl(libs.wasabeef.glideTransformations)
        impl(libs.google.accompanistSystemUiController)
        impl(libs.googleDagger.hiltAndroid)
        api(libs.githubTerrakok.cicerone)
        visifyKapt(libs.googleDagger.hiltCompiler)
    }
)