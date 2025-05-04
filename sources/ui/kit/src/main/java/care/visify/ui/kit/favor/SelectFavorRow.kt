package care.visify.ui.kit.favor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import care.visify.ui.icons.IconsR
import care.visify.ui.kit.containers.VisifyRow
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.mainCellPrimary
import care.visify.ui.kit.theme.values.mainCellTertiary
import care.visify.ui.kit.theme.values.microTertiary
import care.visify.ui.kit.util.clickableNoInteraction

@Composable
fun SelectFavorRow(
    modifier: Modifier = Modifier,
    selectedFavor: String?,
    onSelectClick: () -> Unit,
) {
    VisifyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
            .height(56.dp)
            .clickableNoInteraction(onSelectClick)
    ) {
        if (selectedFavor == null) {
            Text("Услуга", style = VisifyTheme.visifyTypography.mainCellTertiary)
        } else {
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text("Услуга", style = VisifyTheme.visifyTypography.microTertiary)
                Spacer(modifier = Modifier.height(3.dp))
                Text(selectedFavor, style = VisifyTheme.visifyTypography.mainCellPrimary)
            }
        }

        Icon(
            painter = painterResource(id = IconsR.ic_add_entry_24),
            contentDescription = "Add",
            tint = VisifyTheme.colors.frame.active
        )
    }
}