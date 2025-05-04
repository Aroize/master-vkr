package care.visify.ui.kit.components.toggle

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import care.visify.ui.kit.R
import care.visify.ui.kit.containers.VisifyHapticContainer

@Composable
fun VisifyFavoriteToggle(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onClick: () -> Unit,
) {
    val painter = if (enabled) {
        painterResource(id = R.drawable.ic_heart_fav_24)
    } else {
        painterResource(id = R.drawable.ic_heart_24)
    }

    VisifyHapticContainer(
        modifier = modifier,
        onClick = onClick,
    ) {
        Image(
            painter = painter,
            contentDescription = "Favourite"
        )
    }
}