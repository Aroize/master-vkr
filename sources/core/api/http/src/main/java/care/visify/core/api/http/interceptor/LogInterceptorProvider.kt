package care.visify.core.api.http.interceptor

import android.util.Log
import care.visify.core.api.http.BuildConfig
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

object LogInterceptorProvider {

    private const val TAG = "Network"

    private fun loggingInterceptor(): Interceptor {
        val logger = HttpLoggingInterceptor.Logger { message ->
            Log.i(TAG, message)
        }
        val interceptor = HttpLoggingInterceptor(logger).apply {
            level = when {
                BuildConfig.DEBUG -> HttpLoggingInterceptor.Level.BODY
                else -> HttpLoggingInterceptor.Level.BASIC
            }
        }
        return interceptor
    }

    fun interceptor(): Interceptor = loggingInterceptor()
}