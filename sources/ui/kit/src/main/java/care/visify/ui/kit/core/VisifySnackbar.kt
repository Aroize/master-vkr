package care.visify.ui.kit.core

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.mainCellPrimary

@Composable
fun rememberVisifySnackbarHost() = remember { SnackbarHostState() }

@Composable
fun VisifySnackbar(data: SnackbarData) {
    Snackbar(
        contentColor = VisifyTheme.colors.frame.white,
        containerColor = VisifyTheme.colors.frame.grey,
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = data.visuals.message,
            style = VisifyTheme.visifyTypography.mainCellPrimary
        )
    }
}

@Composable
fun VisifySnackbarHost(
    state: SnackbarHostState
) {
    SnackbarHost(
        hostState = state
    ) { data ->
        VisifySnackbar(data = data)
    }
}