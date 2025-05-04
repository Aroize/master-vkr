package care.visify.ui.kit.detailed.feedback

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import care.visify.core.image.Image
import care.visify.ui.icons.IconsR
import care.visify.ui.kit.components.badge.RatingWithStar
import care.visify.ui.kit.components.divider.VisifyDivider
import care.visify.ui.kit.components.image.VisifyImageById
import care.visify.ui.kit.containers.VisifyColumn
import care.visify.ui.kit.imageviewer.ImageViewerContainer
import care.visify.ui.kit.overlay.container.OverlayContainerStateImpl
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.microWhite
import care.visify.ui.kit.theme.values.miniActive
import care.visify.ui.kit.theme.values.miniPrimary
import care.visify.ui.kit.theme.values.miniSecondary
import care.visify.ui.kit.theme.values.miniTertiary
import care.visify.ui.kit.util.clickableNoInteraction
import care.visify.ui.models.feedback.FeedbackUiModel
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import java.time.format.DateTimeFormatter
import java.util.Locale

private const val REVIEW_COLLAPSE_SYMBOLS = 200


@Composable
fun ReviewItem(
    item: FeedbackUiModel,
    modifier: Modifier = Modifier,
    onExpand: () -> Unit,
) {

    val formatter = remember { DateTimeFormatter.ofPattern("d MMMM,", Locale.getDefault()) }

    VisifyColumn(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp, end = 16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = item.date.format(formatter),
                    style = VisifyTheme.visifyTypography.miniTertiary
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = item.author,
                    style = VisifyTheme.visifyTypography.miniSecondary
                )
            }
            RatingWithStar(rating = item.review.rating)
        }

        if (item.wearing != null) {
            Text(
                text = "Есть отзыв после носки",
                style = VisifyTheme.visifyTypography.microWhite,
                modifier = Modifier
                    .padding(start = 16.dp, top = 8.dp)
                    .background(VisifyTheme.colors.label.active, RoundedCornerShape(2.dp))
                    .padding(horizontal = 4.dp, vertical = 2.dp)
            )
        }

        val processedReview by derivedStateOf {
            if (item.review.text.length > REVIEW_COLLAPSE_SYMBOLS)
                "${item.review.text.take(REVIEW_COLLAPSE_SYMBOLS)}..."
            else
                item.review.text
        }

        Text(
            text = processedReview,
            style = VisifyTheme.visifyTypography.miniPrimary,
            modifier = Modifier
                .padding(top = 8.dp, start = 16.dp, end = 16.dp),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )

        if (item.review.images.isNotEmpty() || item.wearing?.images.isNullOrEmpty().not()) {
            Spacer(modifier = Modifier.height(14.dp))
            BadgedCarousel(
                item.review.images,
                item.wearing?.images.orEmpty().toPersistentList(),
            )
        }

        item.organisationReply?.let { reply ->
            Spacer(modifier = Modifier.height(14.dp))
            OrganisationReply(
                reply,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp),
            )
        }

        if (item.review.text.length > REVIEW_COLLAPSE_SYMBOLS || item.wearing != null) {
            Text(
                text = "Смотреть полностью",
                style = VisifyTheme.visifyTypography.miniActive,
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp)
                    .clickableNoInteraction(onExpand)
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        )
    }
}

@Composable
fun BadgedCarousel(
    preWearingImages: PersistentList<Image>,
    wearingImages: PersistentList<Image>,
    padding: PaddingValues = PaddingValues(horizontal = 16.dp),
) {

    val cornerRadius = with(LocalDensity.current) { 6.dp.toPx().toInt() }

    var selectedImage by remember { mutableStateOf(0) }
    val imageContainerState = remember { OverlayContainerStateImpl() }

    Box {
        LazyRow(
            contentPadding = padding,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            itemsIndexed(preWearingImages) { idx, imageId ->
                VisifyImageById(
                    model = imageId,
                    modifier = Modifier
                        .size(80.dp)
                        .background(
                            VisifyTheme.colors.frame.dialogOverlay,
                            RoundedCornerShape(6.dp)
                        )
                        .clickableNoInteraction {
                            selectedImage = idx
                            imageContainerState.show()
                        },
                    requestBuilderTransform = {
                        it.transform(CenterCrop(), RoundedCorners(cornerRadius))
                    }
                )
            }

            itemsIndexed(wearingImages) { idx, imageId ->
                Box {
                    VisifyImageById(
                        model = imageId,
                        modifier = Modifier
                            .size(80.dp)
                            .background(
                                VisifyTheme.colors.frame.dialogOverlay,
                                RoundedCornerShape(6.dp)
                            )
                            .clickableNoInteraction {
                                selectedImage = preWearingImages.size + idx
                                imageContainerState.show()
                            },
                        requestBuilderTransform = {
                            it.transform(
                                CenterCrop(), RoundedCorners(cornerRadius)
                            )
                        }
                    )
                    Image(
                        painter = painterResource(id = IconsR.ic_clock_10),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(5.dp)
                            .background(
                                VisifyTheme.colors.label.active,
                                RoundedCornerShape(2.dp)
                            )
                            .padding(1.5.dp)
                    )
                }
            }
        }

        ImageViewerContainer(
            containerState = imageContainerState,
            images = preWearingImages.addAll(wearingImages),
            startIndex = selectedImage
        )
    }
}

@Composable
fun OrganisationReply(
    reply: String,
    modifier: Modifier = Modifier,
    background: Color = VisifyTheme.colors.frame.grey,
    titleColor: Color = VisifyTheme.colors.label.secondary,
    textColor: Color = VisifyTheme.colors.label.primary,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(background, RoundedCornerShape(6.dp))
            .padding(start = 16.dp, end = 16.dp, top = 17.dp, bottom = 14.dp)
    ) {
        Text(
            text = "Ответ салона",
            style = VisifyTheme.visifyTypography.mini,
            color = titleColor
        )
        Spacer(modifier = Modifier.height(7.dp))
        Text(
            text = reply,
            style = VisifyTheme.visifyTypography.mini,
            color = textColor
        )
    }
}


class ReviewItemColors(
    val reviewColor: Color,
    val dateColor: Color,
    val additionalColor: Color,
    val ratingColor: Color,
    val replyBackgroundColor: Color,
    val replyTitleColor: Color,
) {
    companion object {
        @Composable
        fun light(): ReviewItemColors {
            return ReviewItemColors(
                reviewColor = VisifyTheme.colors.label.primary,
                dateColor = VisifyTheme.colors.label.tertiary,
                additionalColor = VisifyTheme.colors.label.primary,
                ratingColor = VisifyTheme.colors.label.primary,
                replyBackgroundColor = VisifyTheme.colors.frame.grey,
                replyTitleColor = VisifyTheme.colors.label.secondary,
            )
        }

        @Composable
        fun dark(): ReviewItemColors {
            return ReviewItemColors(
                reviewColor = VisifyTheme.colors.label.white,
                dateColor = VisifyTheme.colors.label.tertiary,
                additionalColor = VisifyTheme.colors.label.tertiary,
                ratingColor = VisifyTheme.colors.label.secondary,
                replyBackgroundColor = VisifyTheme.colors.frame.dialogOverlay,
                replyTitleColor = VisifyTheme.colors.label.tertiary,
            )
        }
    }
}


class ExtendedReviewItemsColors(
    val backgroundColor: Color,
    val reviewColors: ReviewItemColors,
) {
    companion object {
        @Composable
        fun light(): ExtendedReviewItemsColors {
            return ExtendedReviewItemsColors(
                backgroundColor = VisifyTheme.colors.label.white,
                reviewColors = ReviewItemColors.light()
            )
        }

        @Composable
        fun dark(): ExtendedReviewItemsColors {
            return ExtendedReviewItemsColors(
                backgroundColor = VisifyTheme.colors.frame.black,
                reviewColors = ReviewItemColors.dark()
            )
        }
    }
}


@Composable
fun ExtendedReviewItem(
    item: FeedbackUiModel,
    colors: ExtendedReviewItemsColors = ExtendedReviewItemsColors.light(),
) {

    val formatter = remember { DateTimeFormatter.ofPattern("d MMMM", Locale.getDefault()) }
    val wearingFormatter = remember { DateTimeFormatter.ofPattern("d MMMM,", Locale.getDefault()) }

    VisifyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {

        ReviewElement(
            element = item.review,
            reply = item.organisationReply,
            formatter = formatter,
            reviewColors = colors.reviewColors
        )

        Spacer(modifier = Modifier.height(16.dp))

        item.wearing?.let { element ->
            VisifyDivider(modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(23.dp))

            ReviewElement(
                element = element,
                afterDate = {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Отзыв после 2-х недель",
                        style = VisifyTheme.visifyTypography.mini,
                        color = colors.reviewColors.additionalColor
                    )
                },
                reply = item.organisationWearingReply,
                formatter = wearingFormatter,
                reviewColors = colors.reviewColors
            )
        }
    }
}

@Composable
fun ReviewElement(
    element: FeedbackUiModel.ReviewUiModel,
    afterDate: @Composable () -> Unit = {},
    reply: String?,
    formatter: DateTimeFormatter,
    reviewColors: ReviewItemColors = ReviewItemColors.light(),
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = element.date.format(formatter),
                style = VisifyTheme.visifyTypography.mini,
                color = reviewColors.dateColor
            )
            afterDate()
        }
        RatingWithStar(rating = element.rating, textColor = reviewColors.ratingColor)
    }

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = element.text,
        style = VisifyTheme.visifyTypography.mini,
        color = reviewColors.reviewColor
    )

    if (element.images.isNotEmpty()) {
        Spacer(modifier = Modifier.height(14.dp))
        BadgedCarousel(
            preWearingImages = element.images,
            wearingImages = persistentListOf(),
            padding = PaddingValues()
        )
    }

    reply?.let {
        Spacer(modifier = Modifier.height(14.dp))
        OrganisationReply(
            reply = it,
            background = reviewColors.replyBackgroundColor,
            titleColor = reviewColors.replyTitleColor,
            textColor = reviewColors.reviewColor,
        )
    }
}