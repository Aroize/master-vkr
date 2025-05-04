package care.visify.ui.kit.master

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility
import care.visify.core.image.Image
import care.visify.ui.kit.components.badge.RatingWithStar
import care.visify.ui.kit.components.divider.VisifyDivider
import care.visify.ui.kit.components.image.VisifyImageById
import care.visify.ui.kit.containers.cellitem.VisifyCellItem
import care.visify.ui.kit.containers.cellitem.toSelectedStateWithGone
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.mainCellPrimary
import care.visify.ui.kit.theme.values.microTertiary
import care.visify.ui.kit.util.clickableNoInteraction
import care.visify.ui.kit.util.formatter.PriceFormatter

@Composable
fun MasterProfessionItem(
    modifier: Modifier = Modifier,
    masterName: String,
    masterSurname: String?,
    avatar: Image,
    profession: String,
    rating: Float,
    favorPrice: Int? = null,
    shape: Shape,
    isSelected: Boolean,
    alpha: Float = 1f,
    enabled: Boolean = true,
    hasDivider: Boolean,
    onClick: () -> Unit,
) {
    VisifyCellItem(
        shape = shape,
        selectedState = isSelected.toSelectedStateWithGone(),
        innerPadding = PaddingValues(end = 16.dp),
        modifier = modifier
            .height(80.dp)
            .alpha(alpha)
            .clickableNoInteraction { if (enabled) onClick() }
    ) { selectedRef ->
        val (avatarRef, nameRef, professionRef, ratingRef, dividerRef, priceRef) = createRefs()

        VisifyImageById(
            model = avatar,
            requestBuilderTransform = { it.circleCrop() },
            modifier = Modifier
                .size(48.dp)
                .constrainAs(avatarRef) {
                    start.linkTo(parent.start, margin = 16.dp)
                    top.linkTo(parent.top, margin = 8.dp)
                },
        )

        val title = "$masterName ${masterSurname.orEmpty()}"

        Text(
            text = title,
            style = VisifyTheme.visifyTypography.mainCellPrimary,
            modifier = Modifier
                .constrainAs(nameRef) {
                    end.linkTo(selectedRef.start, margin = 12.dp)
                    start.linkTo(avatarRef.end, margin = 12.dp)
                    top.linkTo(parent.top, margin = 13.dp)
                    width = Dimension.fillToConstraints
                },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        val chainRef = createHorizontalChain(
            professionRef,
            ratingRef,
            chainStyle = ChainStyle.Packed(0f)
        )
        //todo max chain width
        constrain(chainRef) {
            start.linkTo(nameRef.start)
            end.linkTo(selectedRef.start, margin = 12.dp, goneMargin = 36.dp)
        }

        Text(
            text = profession,
            style = VisifyTheme.visifyTypography.microTertiary,
            modifier = Modifier
                .constrainAs(professionRef) {
                    top.linkTo(nameRef.bottom, margin = 4.dp)
                },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        RatingWithStar(
            rating = rating,
            isSmall = true,
            modifier = Modifier
                .constrainAs(ratingRef) {
                    top.linkTo(professionRef.top)
                    start.linkTo(professionRef.end, margin = 6.dp)
                    bottom.linkTo(professionRef.bottom)
                }
        )

        favorPrice?.let {
            Text(
                text = PriceFormatter.formatCurrency(favorPrice),
                style = VisifyTheme.visifyTypography.microTertiary,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .constrainAs(priceRef) {
                        start.linkTo(professionRef.start)
                        top.linkTo(professionRef.bottom, margin = 3.dp)
                        bottom.linkTo(parent.bottom, margin = 16.dp)
                        width = Dimension.preferredWrapContent
                    },
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        if (hasDivider) {
            VisifyDivider(
                modifier = Modifier
                    .constrainAs(dividerRef) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(nameRef.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                        visibility = when (hasDivider) {
                            true -> Visibility.Visible
                            else -> Visibility.Gone
                        }
                    }
            )
        }
    }
}