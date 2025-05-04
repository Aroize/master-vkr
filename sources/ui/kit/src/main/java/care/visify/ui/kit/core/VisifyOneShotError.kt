package care.visify.ui.kit.core

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@Composable
fun SnackbarHostState.showOneShotError(
    message: String,
    duration: SnackbarDuration = SnackbarDuration.Short
) {
    val scope = rememberCoroutineScope()
    scope.launch {
        showSnackbar(
            message = message,
            actionLabel = null,
            withDismissAction = false,
            duration = duration
        )
    }
}