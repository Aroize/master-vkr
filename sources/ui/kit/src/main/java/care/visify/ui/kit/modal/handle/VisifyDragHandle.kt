package care.visify.ui.kit.modal.handle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.theme.VisifyTheme

@Composable
fun VisifyDragHandle(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(top = 8.dp, bottom = 12.dp)
            .size(32.dp, 4.dp)
            .background(
                VisifyTheme.colors.frame.disabled,
                RoundedCornerShape(4.dp)
            )
    )
}