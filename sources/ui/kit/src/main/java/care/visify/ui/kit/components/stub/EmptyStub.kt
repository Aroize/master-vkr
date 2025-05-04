package care.visify.ui.kit.components.stub

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.components.button.VisifyTextButton
import care.visify.ui.kit.components.stub.EmptyStubDefaults.drawCircles
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.mainTextSecondary
import care.visify.ui.kit.theme.values.subheaderPrimary

@Composable
fun EmptyStub(
    modifier: Modifier = Modifier,
    @DrawableRes
    iconRes: Int,
    @StringRes
    headerRes: Int,
    headerArgs: Array<Any>? = null,
    @StringRes
    hintRes: Int? = null,
    @StringRes
    buttonTextRes: Int? = null,
    onButtonClick: (() -> Unit)? = null,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        Image(
            painter = painterResource(id = iconRes),
            contentDescription = "icon",
            modifier = Modifier
                .padding(
                    start = 36.dp,
                    top = 72.dp,
                    bottom = 72.dp,
                )
                .drawCircles()
        )

        Spacer(modifier = Modifier.height(16.dp))

        val headerString = if (headerArgs == null) {
            stringResource(headerRes)
        } else {
            stringResource(headerRes, *headerArgs)
        }

        Text(
            text = headerString,
            style = VisifyTheme.visifyTypography.subheaderPrimary,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        if (hintRes != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(hintRes),
                style = VisifyTheme.visifyTypography.mainTextSecondary,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        if (buttonTextRes != null && onButtonClick != null) {
            VisifyTextButton(
                stringResource(buttonTextRes),
                modifier = Modifier.padding(horizontal = 16.dp),
                onClick = onButtonClick
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}