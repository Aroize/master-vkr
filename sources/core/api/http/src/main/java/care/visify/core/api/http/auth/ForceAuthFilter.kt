package care.visify.core.api.http.auth

import okhttp3.Request

interface ForceAuthFilter {
    fun isNeedToAuth(request: Request): Boolean
}