package care.visify.client.feature.im.peer

import care.visify.feature.auth.api.CurrentUserHolder
import care.visify.feature.im.api.Peer
import care.visify.feature.im.api.PeerType
import care.visify.feature.im.impl.core.core.PeerProvider
import javax.inject.Inject

class ClientPeerProvider @Inject constructor(
    private val currentUserHolder: CurrentUserHolder,
) : PeerProvider {

    override fun me(): Peer {
        throw UnsupportedOperationException("Unsupported for client")
    }

    override fun isSamePeer(peer: Peer): Boolean {
        if (peer !is Peer.Client) return false
        return peer.clientId == currentUserHolder.userId()
    }

    override fun current(): PeerType = PeerType.CLIENT

    override fun fromChatWith(peer: Peer): Peer {
        return when (peer) {
            is Peer.Org -> Peer.Client(
                orgId = peer.orgId,
                clientId = currentUserHolder.userId()
            )
            Peer.Support -> Peer.Client(
                orgId = -1,
                clientId = currentUserHolder.userId()
            )
            else -> throw UnsupportedOperationException("Unsupported chat with $peer")
        }
    }
}