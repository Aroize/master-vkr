package care.visify.ui.kit.feedback

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import care.visify.ui.kit.components.button.VisifyButton
import care.visify.ui.kit.modal.VisifyModalBottomSheet
import care.visify.ui.kit.modal.VisifySheetState
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.mainTextSecondary
import care.visify.ui.kit.theme.values.subheaderPrimary


@Composable
fun SucceedFeedbackBottomSheet(state: VisifySheetState<Unit>) {
    VisifyModalBottomSheet(visifySheetState = state) {

        Text(
            text = "Отзыв успешно оставлен",
            style = VisifyTheme.visifyTypography.subheaderPrimary,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "\uD83C\uDF89",
            fontSize = 73.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Как только салон выложит работу, к нему сразу добавится ваш отзыв",
            style = VisifyTheme.visifyTypography.mainTextSecondary,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        VisifyButton(
            label = "Отлично",
            onClick = { state.hide() },
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}