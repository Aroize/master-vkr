package care.visify.business.core.network

import care.visify.api.models.chatting.AttachmentDeserializer
import care.visify.api.models.chatting.AttachmentRequest
import care.visify.api.models.chatting.AttachmentResponse
import care.visify.business.core.auth.BusinessAuthListener
import care.visify.business.core.network.serializers.EventWithIdAdapter
import care.visify.business.core.network.serializers.LocalDateTimeAdapter
import care.visify.business.core.network.serializers.LocalTimeAdapter
import care.visify.business.core.network.serializers.UUIDAdapter
import care.visify.core.api.http.auth.AppAuthListener
import care.visify.core.api.sse.engine.handler.EventWithId
import care.visify.core.bus.EventBus
import care.visify.core.ktx.CoroutineDispatchers
import care.visify.core.navigator.api.VisifyRouter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.UUID
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter())
        .registerTypeAdapter(LocalTime::class.java, LocalTimeAdapter())
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
        dispatchers: CoroutineDispatchers,
    ): AppAuthListener = BusinessAuthListener(bus, router, dispatchers)
}