package care.visify.ui.kit.modal.common

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import care.visify.ui.kit.components.button.VisifyTextButton
import care.visify.ui.kit.modal.VisifyModalBottomSheet
import care.visify.ui.kit.modal.VisifySheetState
import care.visify.ui.kit.modal.headers.VisifyModalHeader
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.mainTextPrimary

@Composable
fun OrderAlmostEditSheet(
    state: VisifySheetState<Unit>,
    @StringRes
    headerRes: Int,
    @StringRes
    descRes: Int,
    navigateToChatScreen: () -> Unit,
) {
    VisifyModalBottomSheet(
        visifySheetState = state,
        header = {
            VisifyModalHeader(titleText = stringResource(id = headerRes))
        })
    {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "\uD83C\uDF89",
                fontSize = 73.sp,
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(id = descRes),
                style = VisifyTheme.visifyTypography.mainTextPrimary,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Переписку можете видеть в сообщениях",
                style = VisifyTheme.visifyTypography.mainTextPrimary,
            )

            VisifyTextButton(text = "Открыть переписку") {
                state.hide()
                navigateToChatScreen()
            }

            Spacer(modifier = Modifier.height(8.dp))

        }
    }
}