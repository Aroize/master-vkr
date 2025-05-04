package care.visify.ui.kit.components.stub

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.R
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.headerLargePrimary
import care.visify.ui.kit.util.asBgColor

@Composable
fun AuthStubScreen(
    @StringRes
    titleRes: Int,
    @DrawableRes
    iconRes: Int,
    onSignInClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .asBgColor(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Spacer(modifier = Modifier.height(64.dp))
            Text(
                text = stringResource(titleRes),
                style = VisifyTheme.visifyTypography.headerLargePrimary,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
        }

        Column {
            EmptyStub(
                iconRes = iconRes,
                headerRes = R.string.auth_stub_header,
                hintRes = R.string.auth_stub_hint,
                buttonTextRes = R.string.auth_stub_button,
                onButtonClick = onSignInClick,
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}