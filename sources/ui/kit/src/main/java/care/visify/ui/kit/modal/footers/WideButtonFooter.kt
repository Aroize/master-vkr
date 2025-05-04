package care.visify.ui.kit.modal.footers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.components.button.VisifyButton
import care.visify.ui.kit.components.divider.VisifyDivider
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.microSecondary

@Composable
fun WideButtonFooter(
    label: String,
    enabled: Boolean,
    descText: String? = null,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .background(VisifyTheme.colors.frame.white)
            .navigationBarsPadding()
    ) {
        VisifyDivider(
            Modifier
                .fillMaxWidth()
        )

        descText?.let {
            Spacer(Modifier.height(16.dp))
            Text(
                descText,
                style = VisifyTheme.visifyTypography.microSecondary,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(Modifier.height(4.dp))
        }

        VisifyButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 16.dp),
            label = label,
            onClick = onClick,
            enabled = enabled,
        )
    }
}