package care.visify.core.api.http.auth

interface AppAuthListener {
    fun onAuthSucceed()
    fun onAuthFailed()
}