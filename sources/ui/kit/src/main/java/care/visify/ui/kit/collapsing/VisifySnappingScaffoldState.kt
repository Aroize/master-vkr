package care.visify.ui.kit.collapsing

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class VisifySnappingScaffoldState(
    internal val canConsumeScroll: State<Boolean>,
    internal val topStateScrollConsumer: (Float) -> Float
) {
    var nestedScrollConnection: SnappingNestedScrollConnection by mutableStateOf(object : SnappingNestedScrollConnection {})
        internal set
}