package care.visify.ui.kit.modal.footers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import care.visify.ui.kit.theme.VisifyTheme

@Composable
fun VisifyEmptyFooter() {
    Box(
        modifier = Modifier
            .background(VisifyTheme.colors.frame.white)
            .navigationBarsPadding()
    )
}