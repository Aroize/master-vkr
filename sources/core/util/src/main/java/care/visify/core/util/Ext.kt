package care.visify.core.util

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList


fun Boolean.toInt() = if (this) 1 else 0

public fun <T : Any> persistentListOfNotNull(vararg elements: T?): PersistentList<T> =
    elements.filterNotNull().toPersistentList()