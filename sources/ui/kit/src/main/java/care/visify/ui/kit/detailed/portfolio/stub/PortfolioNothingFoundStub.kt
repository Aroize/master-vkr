package care.visify.ui.kit.detailed.portfolio.stub

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
import androidx.compose.ui.unit.dp
import care.visify.ui.icons.IconsR
import care.visify.ui.kit.components.button.VisifyTextButton
import care.visify.ui.kit.components.stub.EmptyStubDefaults.drawCircles
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.subheaderPrimary

@Composable
fun PortfolioNothingFoundStub(
    modifier: Modifier = Modifier, onClearFiltersClick: (() -> Unit)
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        Image(
            painter = painterResource(id = IconsR.ic_stub_portfolio_24),
            contentDescription = "icon",
            modifier = Modifier
                .padding(
                    start = 36.dp,
                    top = 72.dp,
                    bottom = 72.dp,
                )
                .drawCircles()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Ничего не найдено",
            style = VisifyTheme.visifyTypography.subheaderPrimary,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        VisifyTextButton(
            "Сбросить фильтры",
            modifier = Modifier.padding(horizontal = 16.dp),
            onClick = onClearFiltersClick
        )

    }
}
