package care.visify.client

import android.app.Application
import care.visify.client.core.init.ClientAppInitializer
import care.visify.core.api.http.base.HttpClientProvider
import care.visify.core.api.http.hilt.DefaultHttpClientQualifier
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@HiltAndroidApp
class ClientApp : Application() {

    @Inject
    @DefaultHttpClientQualifier
    lateinit var httpClientProvider: HttpClientProvider

    @Inject
    lateinit var initializer: ClientAppInitializer

    override fun onCreate() {
        super.onCreate()
        initializer.initializeApplication()
    }
}