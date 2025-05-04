package care.visify.core.api.http.base

import care.visify.core.pref.PreferencesFactory

class ApiHostManager(
    preferencesFactory: PreferencesFactory,
) {

    enum class ApiHost(val host: String) {
        PROD("api.visify.care"),
        DEV("dev-api.visify.care"),
        LOCAL_WIFI("192.168.0.77"),
        LOCAL_HOST("10.0.2.2"),
    }

    private val hostPreference = preferencesFactory.stringPreference(HOST_KEY)

    private val cachedHost: String by lazy { hostPreference.get() }

    fun getHost(): ApiHost =
        if (cachedHost == "") {
            ApiHost.DEV.also {
                setHost(it)
            }
        } else {
            ApiHost.valueOf(cachedHost)
        }

    fun setHost(apiHost: ApiHost) {
        hostPreference.put(apiHost.name)
    }

    companion object {
        private const val HOST_KEY = "client.net.host"
    }
}