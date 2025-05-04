package care.visify.ui.kit.core

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import care.visify.ui.kit.theme.VisifyTheme


// todo(): add fab
// todo(): add ptr
@Composable
fun VisifyScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {  },
    bottomBar: @Composable () -> Unit = {  },
    snackbarHost: SnackbarHostState? = null,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        snackbarHost = {
            snackbarHost?.let { host ->
                VisifySnackbarHost(state = host)
            }
        },
        containerColor = VisifyTheme.colors.frame.grey,
        topBar = topBar,
        bottomBar = bottomBar,
        content = content
    )
}