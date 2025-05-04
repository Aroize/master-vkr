package care.visify.business.feature.im.peer

import care.visify.feature.auth.api.CurrentUserHolder
import care.visify.feature.employee.api.EmployeeRoleManager
import care.visify.feature.im.api.Peer
import care.visify.feature.im.api.PeerType
import care.visify.feature.im.impl.core.core.PeerProvider
import javax.inject.Inject

class BusinessPeerProvider @Inject constructor(
    private val currentUserHolder: CurrentUserHolder,
    private val employeeRoleManager: EmployeeRoleManager
) : PeerProvider {
    override fun me(): Peer {
        val role = employeeRoleManager.get()
            ?: throw IllegalArgumentException("Select organisation before you can get Peer")
        return Peer.Org(role.orgId)
    }

    override fun isSamePeer(peer: Peer): Boolean {
        if (peer !is Peer.Org) return false
        return peer == me()
    }

    override fun current(): PeerType = PeerType.ORG

    override fun fromChatWith(peer: Peer): Peer {
        return when (peer) {
            is Peer.Client -> Peer.Org(orgId = peer.orgId)
            Peer.Support -> Peer.Client(
                orgId = -1,
                clientId = currentUserHolder.userId()
            )

            else -> throw UnsupportedOperationException("Unsupported chat with $peer")
        }
    }
}