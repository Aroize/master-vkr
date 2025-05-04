package care.visify.core.api.http.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import care.visify.core.bus.EventBus
import care.visify.core.bus.NetworkStateEvent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class NetworkManager @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val bus: EventBus
): ConnectivityManager.NetworkCallback() {

    fun start() {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
        val connectivityManager = context
            .getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        connectivityManager
            .registerNetworkCallback(networkRequest, this)
    }


    override fun onAvailable(network: Network) {
        runBlocking { bus.publish(NetworkStateEvent(isNetworkAvailable = true)) }
    }

    override fun onLost(network: Network) {
        runBlocking { bus.publish(NetworkStateEvent(isNetworkAvailable = false)) }
    }
}