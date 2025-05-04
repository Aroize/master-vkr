package care.visify.ui.kit.components.footer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.components.divider.VisifyDivider
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.buttonPrimary
import care.visify.ui.kit.theme.values.buttonTertiary
import care.visify.ui.kit.util.clickableNoInteraction
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun SelectionFooter(
    selectedItems: MutableState<PersistentList<Int>>,
    onClose: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(VisifyTheme.colors.frame.white)
    ) {
        VisifyDivider(modifier = Modifier.fillMaxWidth())
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                text = "Сбросить",
                style = when {
                    selectedItems.value.isEmpty() -> VisifyTheme.visifyTypography.buttonTertiary
                    else -> VisifyTheme.visifyTypography.buttonPrimary
                },
                modifier = Modifier
                    .clickableNoInteraction {
                        if (selectedItems.value.isNotEmpty())
                            selectedItems.value = persistentListOf()
                    }
            )
            Text(
                text = "Выбрать",
                style = when {
                    selectedItems.value.isEmpty() -> VisifyTheme.visifyTypography.buttonTertiary
                    else -> VisifyTheme.visifyTypography.buttonPrimary
                },
                modifier = Modifier
                    .clickableNoInteraction {
                        if (selectedItems.value.isNotEmpty()) {
                            onClose()
                        }
                    }
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}