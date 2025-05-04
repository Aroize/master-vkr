package care.visify.ui.kit.containers.cellitem

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import care.visify.ui.icons.IconsR
import care.visify.ui.kit.components.divider.VisifyDivider
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.mainCellTertiary
import care.visify.ui.kit.theme.values.microTertiary
import care.visify.ui.kit.util.clickableNoInteraction


@Composable
fun TextHintInfoCellItem(
    modifier: Modifier = Modifier,
    text: AnnotatedString?,
    hint: String,
    hasDivider: Boolean = false,
    isEnable: Boolean = true,
    showEditIcon: Boolean = false,
    textStyle: TextStyle = VisifyTheme.visifyTypography.mainCell,
    hintStyle: TextStyle = VisifyTheme.visifyTypography.microTertiary,
    onClick: () -> Unit = {},
) {
    TextHintInfoCellItemInternal(
        modifier = modifier,
        text = text,
        hint = hint,
        hasDivider = hasDivider,
        isEnable = isEnable,
        showEditIcon = showEditIcon,
        textStyle = textStyle,
        hintStyle = hintStyle,
        onClick = onClick
    )
}

@Composable
fun TextHintInfoCellItem(
    modifier: Modifier = Modifier,
    text: String?,
    hint: String,
    hasDivider: Boolean = false,
    isEnable: Boolean = true,
    showEditIcon: Boolean = false,
    textStyle: TextStyle = VisifyTheme.visifyTypography.mainCell,
    hintStyle: TextStyle = VisifyTheme.visifyTypography.microTertiary,
    onClick: () -> Unit = {},
) {
    TextHintInfoCellItemInternal(
        modifier = modifier,
        text = text?.let { AnnotatedString(it) },
        hint = hint,
        hasDivider = hasDivider,
        isEnable = isEnable,
        showEditIcon = showEditIcon,
        textStyle = textStyle,
        hintStyle = hintStyle,
        onClick = onClick
    )
}

@Composable
private fun TextHintInfoCellItemInternal(
    modifier: Modifier = Modifier,
    text: AnnotatedString?,
    hint: String,
    hasDivider: Boolean = false,
    isEnable: Boolean = true,
    showEditIcon: Boolean = false,
    textStyle: TextStyle = VisifyTheme.visifyTypography.mainCell,
    hintStyle: TextStyle = VisifyTheme.visifyTypography.microTertiary,
    onClick: () -> Unit = {},
) {
    val height = if (text.isNullOrEmpty()) {
        56.dp
    } else {
        64.dp
    }

    Box(
        modifier = modifier
            .height(height)
            .padding(horizontal = 16.dp)
            .clickableNoInteraction { if (isEnable) onClick() },
        contentAlignment = Alignment.Center,
    ) {

        Column(
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            if (!text.isNullOrEmpty()) {
                Text(
                    hint,
                    style = VisifyTheme.visifyTypography.microTertiary,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(Modifier.height(3.dp))
                Text(
                    text,
                    style = VisifyTheme.visifyTypography.mainCell
                )
            } else {
                Text(
                    hint,
                    style = VisifyTheme.visifyTypography.mainCellTertiary,
                    overflow = TextOverflow.Ellipsis,
                )
            }
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

