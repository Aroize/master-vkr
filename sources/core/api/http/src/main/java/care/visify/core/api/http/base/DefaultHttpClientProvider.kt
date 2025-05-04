package care.visify.core.api.http.base

import android.util.Log
import care.visify.core.api.http.interceptor.InterceptorCollector
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import java.time.Duration

class DefaultHttpClientProvider(
    private val interceptorCollector: InterceptorCollector,
    private val authenticator: Authenticator,
    private val verifierManager: LocalhostHostnameVerifierManager,
) : HttpClientProvider {
    override val client: OkHttpClient by lazy { createClient() }

    private fun createClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                interceptorCollector.provideInterceptors()
                    .forEach { addInterceptor(it) }
            }
            .authenticator(authenticator)
            .connectTimeout(Duration.ofMinutes(1))
            .readTimeout(Duration.ofMinutes(1))
            .writeTimeout(Duration.ofMinutes(1))
            .apply {
                Log.d(TAG, "LocalHostnameVerifier enabled = ${verifierManager.isEnable()}")
                if (verifierManager.isEnable()) {
                    hostnameVerifier(verifierManager)
                }
            }
            .build()
    }

    private companion object {
        const val TAG = "DefaultHttpClientProvider"
    }
}