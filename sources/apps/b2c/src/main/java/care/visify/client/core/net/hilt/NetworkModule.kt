package care.visify.client.core.net.hilt

import care.visify.api.models.chatting.AttachmentDeserializer
import care.visify.api.models.chatting.AttachmentRequest
import care.visify.api.models.chatting.AttachmentResponse
import care.visify.client.core.net.interceptors.ClientInterceptorCollector
import care.visify.client.core.net.serializer.EventWithIdAdapter
import care.visify.client.core.net.serializer.LocalDateTimeAdapter
import care.visify.client.core.net.serializer.UUIDAdapter
import care.visify.client.feature.auth.ClientAuthListener
import care.visify.core.api.http.auth.AppAuthListener
import care.visify.core.api.http.interceptor.InterceptorCollector
import care.visify.core.api.sse.engine.handler.EventWithId
import care.visify.core.bus.EventBus
import care.visify.core.ktx.CoroutineDispatchers
import care.visify.core.navigator.api.VisifyRouter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter())
        .registerTypeAdapter(UUID::class.java, UUIDAdapter())
        .registerTypeAdapter(EventWithId::class.java, EventWithIdAdapter())
        .registerTypeAdapter(AttachmentResponse::class.java, AttachmentDeserializer())
        .registerTypeAdapter(AttachmentRequest::class.java, AttachmentDeserializer())
        .serializeNulls()
        .create()

    @Provides
    @Singleton
    fun provideAppAuthListener(
        router: VisifyRouter,
        bus: EventBus,
        dispatchers: CoroutineDispatchers
    ): AppAuthListener = ClientAuthListener(bus, router, dispatchers)
}

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkInterceptorModule {
    @Binds
    @Singleton
    abstract fun bindInterceptorProvider(
        impl: ClientInterceptorCollector
    ): InterceptorCollector
}