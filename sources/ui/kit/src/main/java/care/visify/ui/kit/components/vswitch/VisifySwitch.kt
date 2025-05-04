package care.visify.ui.kit.components.vswitch

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.util.clickableNoInteraction

@Composable
fun VisifySwitch(
    modifier: Modifier = Modifier,
    checked: Boolean,
    enabled: Boolean = true,
    onCheckedClicked: () -> Unit,
) {

    val offset by animateDpAsState(
        targetValue = if (checked) 20.dp else 0.dp,
        label = "VisifySwitchOffset"
    )

    val switchColor = when {
        enabled.not() -> VisifyTheme.colors.frame.dialogOverlay
        checked -> VisifyTheme.colors.label.active
        else -> VisifyTheme.colors.dividers.main
    }

    Box(
        modifier = modifier
            .size(40.dp, 20.dp)
            .background(switchColor, VisifyTheme.cornerShape.circle)
            .clickableNoInteraction {
                if (enabled) onCheckedClicked()
            },

    ) {
        Box(
            modifier = Modifier
                .offset(x = offset)
                .padding(2.dp)
                .size(16.dp)
                .background(
                    VisifyTheme.colors.frame.white,
                    VisifyTheme.cornerShape.circle
                )
        )
    }
}