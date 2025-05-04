package care.visify.core.api.http.flipper

import com.facebook.flipper.plugins.network.NetworkFlipperPlugin

object FlipperNetworkHolder {
    val plugin by lazy { NetworkFlipperPlugin() }
}