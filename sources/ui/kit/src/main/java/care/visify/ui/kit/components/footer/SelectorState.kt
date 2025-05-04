package care.visify.ui.kit.components.footer

import kotlinx.collections.immutable.PersistentList

class SelectorState<T>(
    val selectedIds: PersistentList<Int>,
    val entities: PersistentList<T>
)