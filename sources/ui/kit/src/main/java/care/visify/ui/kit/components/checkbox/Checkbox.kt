package care.visify.ui.kit.components.checkbox

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import care.visify.ui.icons.IconsR
import care.visify.ui.kit.util.clickableNoInteraction

@Composable
fun VisifyCheckbox(
    isChecked: Boolean,
    modifier: Modifier = Modifier,
) {
    //TODO
    if (isChecked) {
        Image(
            painter = painterResource(id = IconsR.ic_checkmark),
            contentDescription = null,
            modifier = modifier
                .size(24.dp)
        )
    }
}

@Composable
fun VisifyRoundedCheckbox(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    onClick: () -> Unit,
) {

    val imageRes = if (isChecked) {
        IconsR.ic_radio_on_24
    } else {
        IconsR.ic_radio_off_24
    }

    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        modifier = modifier
            .size(24.dp)
            .clickableNoInteraction(onClick)
    )
}