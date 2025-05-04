import visify.tools.androidModule
import visify.tools.impl

androidModule(
    pkg = "care.visify.api.models",
    useCompose = false,
    deps = {
        impl(libs.squareupRetrofit2.converterGson)
    }
)