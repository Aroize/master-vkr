package care.visify.ui.kit.detailed.organisation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import care.visify.ui.icons.IconsR
import care.visify.ui.kit.R
import care.visify.ui.kit.components.badge.RatingWithStar
import care.visify.ui.kit.components.image.VisifyImageById
import care.visify.ui.kit.components.text.CheckmarkedTitle
import care.visify.ui.kit.components.toggle.VisifyFavoriteToggle
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.miniPrimary
import care.visify.ui.kit.theme.values.miniSecondary
import care.visify.ui.kit.theme.values.miniTertiary
import care.visify.ui.kit.util.formatter.PriceFormatter
import care.visify.ui.models.org.OrganisationUiModel

@Composable
fun OrganisationItem(
    organisation: OrganisationUiModel,
    modifier: Modifier = Modifier,
    onFavouriteClicked: (Int) -> Unit,
) {
    Column(
        modifier = modifier
            .background(VisifyTheme.colors.frame.white)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        OrganisationHeader(organisation = organisation, onFavouriteClicked = onFavouriteClicked)
        OrganisationSubHeader(
            organisation = organisation,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OrganisationPricing(organisation = organisation)
        Spacer(modifier = Modifier.height(22.dp))
        OrganisationPreviews(organisation = organisation)
        OrganisationComment(organisation = organisation)
    }
}

@Composable
fun OrganisationHeader(
    organisation: OrganisationUiModel,
    onFavouriteClicked: (Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CheckmarkedTitle(name = organisation.name)
        VisifyFavoriteToggle(
            enabled = organisation.isFavourite,
            onClick = {
                onFavouriteClicked(organisation.id)
            }
        )
    }
}

@Composable
fun OrganisationSubHeader(
    organisation: OrganisationUiModel,
    modifier: Modifier = Modifier,
) {
    Spacer(modifier = Modifier.height(1.dp))
    Text(
        text = organisation.favors,
        style = VisifyTheme.visifyTypography.miniSecondary,
        modifier = modifier
    )
    Spacer(modifier = Modifier.height(11.dp))
    Text(
        text = organisation.address,
        style = VisifyTheme.visifyTypography.miniSecondary,
        modifier = modifier
    )
    organisation.operatingTime?.let { opened ->
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = opened,
            style = VisifyTheme.visifyTypography.miniTertiary,
            modifier = modifier
        )
    }
}

@Composable
fun OrganisationPricing(
    organisation: OrganisationUiModel,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RatingWithStar(rating = organisation.rating)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = pluralStringResource(
                    id = R.plurals.org_total_scores,
                    count = organisation.totalRatings,
                    organisation.totalRatings
                ),
                style = VisifyTheme.visifyTypography.miniTertiary
            )
        }
        Text(
            text = stringResource(
                id = R.string.org_average_bill,
                PriceFormatter.formatPricing(organisation.avgBill)
            ),
            style = VisifyTheme.visifyTypography.miniTertiary,
        )
    }
}

@Composable
fun OrganisationPreviews(
    organisation: OrganisationUiModel,
) {
    if (organisation.avatars.isEmpty())
        return
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(organisation.avatars) { imageId ->
            VisifyImageById(
                model = imageId,
                requestBuilderTransform = { it.centerCrop() },
                modifier = Modifier
                    .size(90.dp)
                    .clip(shape = RoundedCornerShape(4.dp))
            )
        }
    }
    Spacer(modifier = Modifier.height(18.dp))
}

@Composable
fun OrganisationComment(
    organisation: OrganisationUiModel,
) {
    val comment = organisation.commentText ?: return
    Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        Image(
            painter = painterResource(id = IconsR.ic_quotation_15),
            contentDescription = "Quotation"
        )
        Text(
            text = comment,
            style = VisifyTheme.visifyTypography.miniPrimary,
            modifier = Modifier
                .padding(start = 4.dp, top = 4.dp)
        )
    }
    Spacer(modifier = Modifier.height(22.dp))
}