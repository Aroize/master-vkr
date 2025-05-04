package care.visify.core.util

import android.content.Context

@Deprecated("Use context from hilt")
object AppContextHolder {

    private lateinit var applicationContext: Context

    val context: Context
        get() = applicationContext

    fun init(ctx: Context) {
        applicationContext = ctx
    }
}