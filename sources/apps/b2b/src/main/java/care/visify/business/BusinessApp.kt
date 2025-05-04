package care.visify.business

import android.app.Application
import care.visify.business.core.init.BusinessAppInitializer
import care.visify.core.api.http.base.HttpClientProvider
import care.visify.core.api.http.hilt.DefaultHttpClientQualifier
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class BusinessApp : Application() {

    @Inject
    @DefaultHttpClientQualifier
    lateinit var httpClientProvider: HttpClientProvider

    @Inject
    lateinit var initializer: BusinessAppInitializer

    override fun onCreate() {
        super.onCreate()
        initializer.initializeApp()
    }
}