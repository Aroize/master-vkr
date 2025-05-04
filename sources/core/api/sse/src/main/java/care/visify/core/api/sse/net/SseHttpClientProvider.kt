package care.visify.core.api.sse.net

import android.util.Log
import care.visify.core.api.http.base.HttpClientProvider
import care.visify.core.api.http.base.LocalhostHostnameVerifierManager
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.time.Duration
import java.util.concurrent.TimeUnit

class SseHttpClientProvider(
    private val interceptors: List<Interceptor>,
    private val authenticator: Authenticator,
    private val verifierManager: LocalhostHostnameVerifierManager,
) : HttpClientProvider {

    override val client: OkHttpClient by lazy { createSseClient() }

    private fun createSseClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .apply { interceptors.forEach { addInterceptor(it) } }
            .authenticator(authenticator)
            .connectTimeout(Duration.ofMinutes(1))
            .readTimeout(0L, TimeUnit.MILLISECONDS)
            .apply {
                Log.d(TAG, "LocalHostnameVerifier enabled = ${verifierManager.isEnable()}")
                if (verifierManager.isEnable()) {
                    hostnameVerifier(verifierManager)
                }
            }
            .build()
    }

    private companion object {
        const val TAG = "SseHttpClientProvider"
    }
}