package care.visify.ui.kit.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.R
import care.visify.ui.kit.components.button.VisifyButton
import care.visify.ui.kit.components.indicator.VisifyProgressIndicator
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.buttonActive
import care.visify.ui.kit.theme.values.headerLargePrimary
import care.visify.ui.kit.theme.values.mainTextSecondary
import care.visify.ui.kit.theme.values.subheaderPrimary
import care.visify.ui.kit.util.asBgColor
import care.visify.ui.kit.util.clickableNoInteraction

@Composable
fun BoxScope.Loading() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .matchParentSize()
            .asBgColor()
    ) {
        VisifyProgressIndicator()
    }
}

@Composable
fun Loading(
    paddingValues: PaddingValues = PaddingValues(),
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .asBgColor()
            .padding(paddingValues)
    ) {
        VisifyProgressIndicator()
    }
}


@Composable
fun AuthError(
    title: String,
    navigateToEmailOtp: () -> Unit,
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(64.dp))
        Text(
            text = title,
            style = VisifyTheme.visifyTypography.headerLargePrimary,
        )
        Spacer(modifier = Modifier.height(28.dp))
        Text(
            text = "Войдите в приложение",
            style = VisifyTheme.visifyTypography.subheaderPrimary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Для доступа к вашему избранному, визитам и записи в салоны",
            style = VisifyTheme.visifyTypography.mainTextSecondary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Войти",
            style = VisifyTheme.visifyTypography.buttonActive,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(vertical = 19.dp)
                .clickableNoInteraction(onClick = navigateToEmailOtp)
        )
    }
}

@Composable
fun CommonError(
    onRetryClicked: () -> Unit,
    paddingValues: PaddingValues = PaddingValues(),
    message: String = stringResource(id = R.string.error_description),
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .asBgColor()
            .padding(paddingValues)
    ) {
        Text(
            text = message,
            style = VisifyTheme.visifyTypography.subheaderPrimary,
        )
        Spacer(modifier = Modifier.height(16.dp))
        VisifyButton(
            label = stringResource(id = R.string.error_try_again),
            onClick = onRetryClicked
        )
    }
}