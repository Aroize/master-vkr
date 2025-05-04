package care.visify.ui.kit.components.divider

import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.theme.VisifyTheme

@Composable
fun VisifyDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = 0.5.dp,
    orientation: VisifyDividerOrientation = VisifyDividerOrientation.HORIZONTAL,
    color: Color = VisifyTheme.colors.dividers.main,
) {
    when (orientation) {
        VisifyDividerOrientation.VERTICAL ->
            VerticalDivider(
                modifier = modifier,
                color = color,
                thickness = thickness
            )

        VisifyDividerOrientation.HORIZONTAL ->
            HorizontalDivider(
                modifier = modifier,
                color = color,
                thickness = thickness
            )
    }

}