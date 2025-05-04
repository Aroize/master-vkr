package care.visify.ui.kit.containers

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import care.visify.ui.kit.R
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.subheaderActive
import care.visify.ui.kit.util.clickableNoInteraction

//надо потестить
@Composable
fun VisifyAlertDialog(
    isShow: Boolean,
    onDismissRequest: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    if (isShow) {
        Dialog(onDismissRequest = onDismissRequest) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(6.dp),
                colors = CardColors(
                    containerColor = VisifyTheme.colors.frame.white,
                    contentColor = VisifyTheme.colors.label.primary,
                    disabledContainerColor = VisifyTheme.colors.frame.disabled,
                    disabledContentColor = VisifyTheme.colors.frame.disabled,
                ),
                content = content
            )
        }
    }
}

//TODO ПЕРЕВЕСТИ НА VisifySheetState, не удобно использовать
@Composable
fun VisifyOkAlertDialog(
    isShow: Boolean,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit,
) {
    if (isShow) {
        Dialog(onDismissRequest = onDismissRequest) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(6.dp),
                colors = CardColors(
                    containerColor = VisifyTheme.colors.frame.white,
                    contentColor = VisifyTheme.colors.label.primary,
                    disabledContainerColor = VisifyTheme.colors.frame.disabled,
                    disabledContentColor = VisifyTheme.colors.frame.disabled,
                )
            ) {
                content()
                Text(
                    text = stringResource(id = R.string.visify_ui_kit_alert_dialog_ok),
                    style = VisifyTheme.visifyTypography.subheaderActive,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                        .clickableNoInteraction {
                            onDismissRequest()
                        }
                )
            }
        }
    }
}
