package care.visify.ui.kit.detailed.feedback

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility
import care.visify.ui.kit.components.button.VisifyButton
import care.visify.ui.kit.components.divider.VisifyDivider
import care.visify.ui.kit.components.image.VisifyImageById
import care.visify.ui.kit.containers.cellitem.SelectedState
import care.visify.ui.kit.containers.cellitem.VisifyCellItem
import care.visify.ui.kit.modal.VisifyModalBottomSheet
import care.visify.ui.kit.modal.VisifySheetState
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.mainCellPrimary
import care.visify.ui.kit.theme.values.mainTextSecondary
import care.visify.ui.kit.theme.values.microPrimary
import care.visify.ui.kit.theme.values.microTertiary
import care.visify.ui.kit.theme.values.subheaderPrimary
import care.visify.ui.kit.util.cellShape
import care.visify.ui.kit.util.clickableNoInteraction
import care.visify.ui.kit.util.formatter.TimeFormatter
import care.visify.ui.models.feedback.AbsentReviewUiModel
import kotlinx.collections.immutable.PersistentList

@Composable
fun AbsentFeedbacksBottomSheet(
    state: VisifySheetState<PersistentList<AbsentReviewUiModel>>,
    onAbsentFeedbackSelected: (Int) -> Unit,
) {
    VisifyModalBottomSheet(visifySheetState = state) {

        val items = state.unsafeData

        if (items.isEmpty()) {
            Text(
                text = "Отзыв оставить не получится",
                style = VisifyTheme.visifyTypography.subheaderPrimary,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "К сожалению не нашли не одного вашего заказа в этом салоне.\n\nОставить отзыв могут только клиенты.",
                style = VisifyTheme.visifyTypography.mainTextSecondary,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))
            VisifyButton(
                label = "Понятно",
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onClick = { state.hide() }
            )
        } else {
            Text(
                text = "На какой визит оставить отзыв?",
                style = VisifyTheme.visifyTypography.subheaderPrimary,
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            items.forEachIndexed { index, review ->
                val hasDivider = index != items.lastIndex
                VisifyCellItem(
                    shape = cellShape(items, index, 6.dp),
                    hasDivider = false,
                    selectedState = SelectedState.Gone,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .clickableNoInteraction {
                            state.hide()
                            onAbsentFeedbackSelected(review.orderId)
                        }
                ) { iconRef ->
                    val (logoRef, favorRef, dateRef, masterRef, dividerRef) = createRefs()
                    VisifyImageById(
                        model = review.logo,
                        modifier = Modifier
                            .size(48.dp)
                            .constrainAs(logoRef) {
                                start.linkTo(parent.start)
                                top.linkTo(parent.top, margin = 8.dp)
                            }
                    )

                    Text(
                        text = review.favor,
                        style = VisifyTheme.visifyTypography.mainCellPrimary,
                        modifier = Modifier
                            .constrainAs(favorRef) {
                                top.linkTo(logoRef.top, margin = 5.dp)
                                start.linkTo(logoRef.end, margin = 12.dp)
                                end.linkTo(iconRef.end, margin = 12.dp)
                                width = Dimension.fillToConstraints
                            }
                    )

                    Text(
                        text = TimeFormatter.formatFullWithSlot(review.date),
                        style = VisifyTheme.visifyTypography.microPrimary,
                        modifier = Modifier
                            .constrainAs(dateRef) {
                                top.linkTo(favorRef.bottom, margin = 6.dp)
                                start.linkTo(favorRef.start)
                                end.linkTo(iconRef.end, margin = 12.dp)
                                width = Dimension.fillToConstraints
                            }
                    )

                    Text(
                        text = review.master,
                        style = VisifyTheme.visifyTypography.microTertiary,
                        modifier = Modifier
                            .constrainAs(masterRef) {
                                top.linkTo(dateRef.bottom, margin = 5.dp)
                                start.linkTo(dateRef.start)
                                end.linkTo(iconRef.end, margin = 12.dp)
                                bottom.linkTo(parent.bottom, margin = 16.dp)
                                width = Dimension.fillToConstraints
                            }
                    )

                    VisifyDivider(
                        modifier = Modifier.constrainAs(dividerRef) {
                            bottom.linkTo(parent.bottom)
                            start.linkTo(masterRef.start)
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
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}