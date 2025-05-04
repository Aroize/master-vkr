package care.visify.core.api.sse.hilt

import care.visify.core.api.sse.engine.ServerConnectionEngine
import care.visify.core.api.sse.engine.SseConnectionEngineImpl
import care.visify.core.api.sse.engine.handler.EventWithIdParser
import care.visify.core.api.sse.engine.handler.EventWithIdParserImpl
import care.visify.core.api.sse.engine.handler.SseEventHandler
import care.visify.core.api.sse.engine.handler.SseEventHandlerImpl
import care.visify.core.api.sse.repository.RemoteEventRepository
import care.visify.core.api.sse.repository.RemoteEventRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class SseEngineModule {

    @Binds
    @Singleton
    abstract fun bindEventWithIdParser(
        impl: EventWithIdParserImpl
    ): EventWithIdParser

    @Binds
    @Singleton
    abstract fun bindSseEventHandler(
        eventHandler: SseEventHandlerImpl
    ): SseEventHandler

    @Binds
    @Singleton
    abstract fun bindConnectionEngine(
        impl: SseConnectionEngineImpl
    ): ServerConnectionEngine

    @Binds
    @Singleton
    abstract fun bindRemoteEventRepository(
        impl: RemoteEventRepositoryImpl
    ): RemoteEventRepository
}