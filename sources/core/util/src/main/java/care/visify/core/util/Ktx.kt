package care.visify.core.util

import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.coroutineContext

/**
 * @param period ticker period im ms
 * @param delay ticker delay in ms
 */
fun tickerFlow(period: Long, delay: Long = 0L) = flow {
    delay(delay)
    while (true) {
        coroutineContext.ensureActive()
        emit(Unit)
        delay(period)
    }
}

//Int to Enum
inline fun <reified T : Enum<T>> Int.toEnum(): T? {
    return enumValues<T>().firstOrNull { it.ordinal == this }
}