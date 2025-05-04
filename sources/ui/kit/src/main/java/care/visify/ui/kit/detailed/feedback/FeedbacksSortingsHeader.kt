package care.visify.ui.kit.detailed.feedback

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import care.visify.ui.icons.IconsR
import care.visify.ui.kit.R
import care.visify.ui.kit.containers.sheet.sorting.Sorting
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.miniPrimary
import care.visify.ui.kit.theme.values.subheaderPrimary
import care.visify.ui.kit.util.clickableNoInteraction

@Composable
fun FeedbacksSortingsHeader(
    totalFeedbacks: Int,
    sorting: Sorting?,
    onSortingSelectorClick: () -> Unit,
) {
    val shape =
        if (totalFeedbacks == 0)
            VisifyTheme.cornerShape.rounded6dp
        else
            VisifyTheme.cornerShape.roundedTop6dp
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(VisifyTheme.colors.frame.white, shape)
            .padding(horizontal = 16.dp, vertical = 17.dp)
    ) {
        Text(
            text = pluralStringResource(
                id = R.plurals.review_count,
                count = totalFeedbacks,
                totalFeedbacks
            ),
            style = VisifyTheme.visifyTypography.subheaderPrimary,
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickableNoInteraction(onSortingSelectorClick)
        ) {
            sorting?.let { sorting ->
                Text(
                    text = stringResource(id = sorting.name),
                    style = VisifyTheme.visifyTypography.miniPrimary,
                )
                Image(
                    painter = painterResource(id = IconsR.ic_dropdown_24),
                    contentDescription = "Dropdown"
                )
            }
        }
    }
}