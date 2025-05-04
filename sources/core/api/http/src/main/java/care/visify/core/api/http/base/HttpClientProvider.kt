package care.visify.core.api.http.base

import okhttp3.OkHttpClient

interface HttpClientProvider {
    val client: OkHttpClient
}