package care.visify.core.bus

import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterIsInstance
import kotlin.coroutines.coroutineContext

interface EventBus {
    val events: SharedFlow<Event>

    suspend fun<T: Event> publish(event: T)

    fun<T: Event> tryToPublish(event: T)
}

suspend inline fun<reified T : Event> EventBus.subscribe(crossinline onEvent: suspend (T) -> Unit) {
    events.filterIsInstance<T>()
        .collectLatest { event ->
            coroutineContext.ensureActive()
            onEvent(event)
        }
}

suspend inline fun<T: Event> Flow<T>.subscribe(crossinline onEvent: (T) -> Unit) {
    collectLatest { event ->
        coroutineContext.ensureActive()
        onEvent(event)
    }
}

internal class EventBusImpl : EventBus {
    private val _events = MutableSharedFlow<Event>()

    override val events: SharedFlow<Event> = _events.asSharedFlow()

    override suspend fun <T : Event> publish(event: T) {
        _events.emit(event)
    }

    override fun <T : Event> tryToPublish(event: T) {
        _events.tryEmit(event)
    }
}