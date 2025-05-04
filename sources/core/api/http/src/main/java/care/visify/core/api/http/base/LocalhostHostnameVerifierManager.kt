package care.visify.core.api.http.base

import care.visify.core.pref.PreferencesFactory
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession

class LocalhostHostnameVerifierManager(
    preferencesFactory: PreferencesFactory,
) : HostnameVerifier {

    override fun verify(hostname: String?, session: SSLSession?): Boolean {
        return hostname == ApiHostManager.ApiHost.LOCAL_HOST.host
    }

    private val verifierPreference =
        preferencesFactory.booleanPreference(LOCALHOST_HOSTNAME_VERIFIER_KEY)

    fun isEnable() : Boolean = verifierPreference.get()

    fun set(isEnable : Boolean) = verifierPreference.put(isEnable)

    companion object {
        const val LOCALHOST_HOSTNAME_VERIFIER_KEY = "client.net.hostname_verifier"
    }
}