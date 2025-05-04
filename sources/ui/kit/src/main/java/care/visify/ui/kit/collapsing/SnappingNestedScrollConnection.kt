package care.visify.ui.kit.collapsing

import androidx.compose.ui.input.nestedscroll.NestedScrollConnection

interface SnappingNestedScrollConnection : NestedScrollConnection {
    suspend fun onFullSnapRequested() = Unit
}