package care.visify.client.feature.im.peer

import care.visify.feature.im.impl.core.core.PeerProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ImPeerModule {
    @Binds
    @Singleton
    abstract fun imPeerProvider(impl: ClientPeerProvider): PeerProvider
}