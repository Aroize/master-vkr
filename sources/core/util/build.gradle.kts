
import visify.tools.androidModule
import visify.tools.coreDesugaring
import visify.tools.impl

androidModule(
    pkg = "care.visify.core.util",
    useCompose = false,
    desugaringEnabled = true,
    deps = {
        impl(libs.androidx.coreKtx)
        impl(libs.androidx.annotation)
        impl(libs.androidx.lifecycleExtensions)
        coreDesugaring(libs.androidTools.desugarJdkLibs)

        impl(libs.jetbrains.kotlinxCollectionsImmutable)
        impl(libs.jetbrains.kotlinxCoroutinesCore)
    }
)