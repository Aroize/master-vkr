package care.visify.ui.kit.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import care.visify.core.image.Image
import care.visify.ui.kit.components.image.VisifyImageById
import care.visify.ui.kit.containers.VisifyRow
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.mainCellPrimary
import care.visify.ui.kit.theme.values.microTertiary

@Composable
fun UserSmallDescItem(
    modifier: Modifier = Modifier,
    avatar: Image?,
    title: String,
    desc: String,
) {
    VisifyRow(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.height(64.dp),
        contentPadding = PaddingValues(start = 16.dp)
    ) {
        //todo default avatar
        avatar?.let { avatar ->
            VisifyImageById(
                model = avatar,
                requestBuilderTransform = { it.circleCrop() },
                modifier = Modifier
                    .size(48.dp)
            )
        }

        Spacer(modifier = Modifier.width(VisifyTheme.padding._12dp))

        Column {
            Text(
                text = title,
                style = VisifyTheme.visifyTypography.mainCellPrimary,
                modifier = Modifier,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(VisifyTheme.padding._4dp))

            Text(
                text = desc,
                style = VisifyTheme.visifyTypography.microTertiary,
                modifier = Modifier,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}