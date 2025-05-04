package care.visify.ui.kit.detailed.feedback

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.R
import care.visify.ui.kit.containers.VisifyColumn
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.buttonTertiary
import care.visify.ui.kit.theme.values.miniSecondary
import care.visify.ui.kit.theme.values.subheaderPrimary
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf


@Immutable
data class ReviewsHistogramState(
    val rating: Float = 0f,
    val maxRating: Int = 0,
    val histogram: PersistentList<Int> = persistentListOf(),
)

@Composable
fun ReviewsHistogram(
    state: ReviewsHistogramState,
    modifier: Modifier = Modifier
) {
    VisifyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(VisifyTheme.padding._16dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp),
        ) {
            Text(
                text = "Общий рейтинг",
                style = VisifyTheme.visifyTypography.subheaderPrimary
            )
            Text(
                text = "%.2f / %d".format(state.rating, state.maxRating)
                    .replace(".", ","),
                style = VisifyTheme.visifyTypography.buttonTertiary
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        val total by derivedStateOf { state.histogram.sum() }
        state.histogram
            .indices
            .reversed()
            .associate {it + 1 to state.histogram[it] }
            .also { histogram ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Stars(values = histogram.keys)
                    Bars(values = histogram.values, total = total)
                }
            }
    }
}



@Composable
fun Stars(values: Collection<Int>) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        values.forEach { stars ->
            Text(
                text = pluralStringResource(
                    id = R.plurals.review_stars,
                    count = stars,
                    stars
                ),
                style = VisifyTheme.visifyTypography.miniSecondary,
                maxLines = 1,
                textAlign = TextAlign.Start
            )
        }
    }
}

@Composable
fun Bars(values: Collection<Int>, total: Int) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        values.forEach { value ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LinearProgressIndicator(
                    progress = { value.toFloat() / total },
                    color = VisifyTheme.colors.frame.yellow,
                    trackColor = VisifyTheme.colors.frame.grey,
                    strokeCap = StrokeCap.Round,
                    modifier = Modifier.height(6.dp)
                )
                Text(
                    text = "$value",
                    style = VisifyTheme.visifyTypography.miniSecondary,
                    modifier = Modifier.width(20.dp),
                    textAlign = TextAlign.End
                )
            }
        }
    }
}