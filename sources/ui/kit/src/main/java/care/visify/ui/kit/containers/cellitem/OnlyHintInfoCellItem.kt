package care.visify.ui.kit.containers.cellitem

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import care.visify.ui.icons.IconsR
import care.visify.ui.kit.components.divider.VisifyDivider
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.mainCellTertiary
import care.visify.ui.kit.util.clickableNoInteraction


@Composable
fun OnlyHintInfoCellItem(
    modifier: Modifier = Modifier,
    hint: String,
    hasDivider: Boolean = false,
    isEnable: Boolean = true,
    showEditIcon: Boolean = false,
    onClick: () -> Unit = {},
) {
    HintInfoCellItemInternal(
        modifier = modifier,
        hint = hint,
        hasDivider = hasDivider,
        isEnable = isEnable,
        showEditIcon = showEditIcon,
        onClick = onClick
    )
}

@Composable
private fun HintInfoCellItemInternal(
    modifier: Modifier = Modifier,
    hint: String,
    hasDivider: Boolean = false,
    isEnable: Boolean = true,
    showEditIcon: Boolean = false,
    onClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .height(56.dp)
            .padding(horizontal = 16.dp)
            .clickableNoInteraction { if (isEnable) onClick() },
        contentAlignment = Alignment.Center,
    ) {

        Column(
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Text(
                hint,
                style = VisifyTheme.visifyTypography.mainCellTertiary,
                overflow = TextOverflow.Ellipsis,
            )
        }

        if (showEditIcon) {
            Icon(
                painterResource(IconsR.ic_add_entry_24),
                contentDescription = "Add",
                modifier = Modifier.align(Alignment.CenterEnd),
                tint = VisifyTheme.colors.label.active
            )
        }

        if (hasDivider) {
            VisifyDivider(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}