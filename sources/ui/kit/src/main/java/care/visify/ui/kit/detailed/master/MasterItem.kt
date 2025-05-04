package care.visify.ui.kit.detailed.master

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility
import care.visify.ui.kit.components.badge.RatingWithStar
import care.visify.ui.kit.components.divider.VisifyDivider
import care.visify.ui.kit.components.image.VisifyImageById
import care.visify.ui.kit.containers.cellitem.SelectedState
import care.visify.ui.kit.containers.cellitem.VisifyCellItem
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.mainCellPrimary
import care.visify.ui.kit.theme.values.microTertiary
import care.visify.ui.models.master.MasterUiModel

enum class MasterRating {
    ABSENT, END, PROFESSION
}

@Composable
fun MasterItem(
    master: MasterUiModel,
    shape: Shape,
    hasDivider: Boolean,
    modifier: Modifier = Modifier,
    masterRating: MasterRating = MasterRating.END,
    selectedState: SelectedState = SelectedState.Gone
) {
    VisifyCellItem(
        shape = shape,
        hasDivider = false,
        selectedState = selectedState,
        modifier = modifier
    ) { selectedRef ->
        val (avatarRef, nameRef, professionRef, ratingRef, dividerRef) = createRefs()

        if (masterRating == MasterRating.PROFESSION) {
            val chainRef = createHorizontalChain(
                professionRef,
                ratingRef,
                chainStyle = ChainStyle.Packed(0f)
            )
            constrain(chainRef) {
                start.linkTo(nameRef.start)
                end.linkTo(selectedRef.start, margin = 12.dp, goneMargin = 36.dp)
            }
        }


        VisifyImageById(
            model = master.avatar,
            requestBuilderTransform = { it.circleCrop() },
            modifier = Modifier
                .size(48.dp)
                .constrainAs(avatarRef) {
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom, margin = 8.dp)
                    top.linkTo(parent.top, margin = 8.dp)
                },
        )

        val title = "${master.name} ${master.surname.orEmpty()}"
        Text(
            text = title,
            style = VisifyTheme.visifyTypography.mainCellPrimary,
            modifier = Modifier
                .constrainAs(nameRef) {
                    when (masterRating) {
                        MasterRating.END -> end.linkTo(ratingRef.start, margin = 12.dp)
                        else -> end.linkTo(selectedRef.start, margin = 12.dp)
                    }
                    start.linkTo(avatarRef.end, margin = 12.dp)
                    top.linkTo(avatarRef.top, margin = 5.dp)
                    width = Dimension.fillToConstraints
                },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = master.profession,
            style = VisifyTheme.visifyTypography.microTertiary,
            modifier = Modifier
                .then(
                    if (masterRating == MasterRating.PROFESSION)
                        Modifier.padding(end = 8.dp)
                    else
                        Modifier
                )
                .constrainAs(professionRef) {
                    start.linkTo(nameRef.start)
                    top.linkTo(nameRef.bottom, margin = 4.dp)
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    width = when (masterRating) {
                        MasterRating.PROFESSION -> {
                            Dimension.preferredWrapContent
                        }

                        else -> {
                            end.linkTo(selectedRef.start)
                            Dimension.fillToConstraints
                        }
                    }
                },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        RatingWithStar(
            rating = master.rating,
            isSmall = masterRating == MasterRating.PROFESSION,
            modifier = Modifier
                .constrainAs(ratingRef) {
                    when (masterRating) {
                        MasterRating.ABSENT -> {
                            visibility = Visibility.Gone
                        }

                        MasterRating.END -> {
                            top.linkTo(nameRef.top)
                            end.linkTo(parent.end)
                            bottom.linkTo(nameRef.bottom)
                        }

                        MasterRating.PROFESSION -> {
                            top.linkTo(professionRef.top)
                            bottom.linkTo(professionRef.bottom)
                        }
                    }
                }
        )

        if (hasDivider) {
            VisifyDivider(
                modifier = Modifier
                    .constrainAs(dividerRef) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(nameRef.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
            )
        }
    }
}