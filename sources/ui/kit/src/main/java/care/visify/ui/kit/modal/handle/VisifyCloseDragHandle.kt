package care.visify.ui.kit.modal.handle

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.R
import care.visify.ui.kit.containers.sheet.VisifySheetStateOLD
import care.visify.ui.kit.util.clickableNoInteraction
import care.visify.ui.kit.modal.VisifySheetState as VisifySheetStateNew

@Deprecated("Change from deprecated state to new state")
@Composable
fun VisifyCloseDragHandle(sheetState: VisifySheetStateOLD<*>) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_close_24),
            contentDescription = null,
            modifier = Modifier.clickableNoInteraction {
                sheetState.hide()
            }
        )
    }
}

@Composable
fun VisifyCloseDragHandle(sheetState: VisifySheetStateNew<*>) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_close_24),
            contentDescription = null,
            modifier = Modifier.clickableNoInteraction {
                sheetState.hide()
            }
        )
    }
}