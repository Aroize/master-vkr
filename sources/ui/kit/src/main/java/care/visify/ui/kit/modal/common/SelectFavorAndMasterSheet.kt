package care.visify.ui.kit.modal.common

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.containers.cellitem.VisifyTextCellItem
import care.visify.ui.kit.containers.cellitem.toSelectedStateWithGone
import care.visify.ui.kit.detailed.favor.FavorExpandableItem
import care.visify.ui.kit.detailed.favor.FavorStyle
import care.visify.ui.kit.detailed.master.MasterRating
import care.visify.ui.kit.detailed.master.OrganisationMastersItem
import care.visify.ui.kit.modal.VisifyModalBottomSheet
import care.visify.ui.kit.modal.VisifySheetState
import care.visify.ui.kit.modal.footers.SelectBackwardModalFooter
import care.visify.ui.kit.modal.footers.SelectModalFooter
import care.visify.ui.kit.modal.headers.VisifyModalHeader
import care.visify.ui.kit.util.clickableNoInteraction


@Composable
fun SelectFavorAndMasterSheet(
    state: VisifySheetState<FavorsAndMastersState>,
    onFavorClick: (Int) -> Unit,
    onSelectFavorClick: () -> Unit,
    onSelectMasterClick: () -> Unit,
    onMasterClick: (Int) -> Unit,
    onAnyMasterClick: () -> Unit,
    onMasterBackwardClick: () -> Unit,
) {

    val actualState = state.data ?: return
    val currentPage = actualState.page

    val title =
        if (currentPage == FavorsAndMastersState.SelectPage.FAVORS) {
            "Услуга"
        } else {
            "Мастер"
        }

    VisifyModalBottomSheet(
        visifySheetState = state,
        header = { VisifyModalHeader(titleText = title) },
        footer = {
            if (currentPage == FavorsAndMastersState.SelectPage.FAVORS) {
                SelectModalFooter(
                    isEnable = actualState.selectedFavorId != null,
                    onSelectClick = onSelectFavorClick
                )
            } else {
                SelectBackwardModalFooter(
                    isSelectEnable = actualState.selectedMasterId != null || actualState.isAnyMasterSelected,
                    onSelectClick = onSelectMasterClick, onBackwardClick = onMasterBackwardClick
                )
            }
        },
        contentModifier = Modifier.fillMaxSize()
    ) {
        AnimatedContent(
            targetState = currentPage,
            label = "FavorsAndMastersAnimator",
            transitionSpec = {
                when (targetState) {
                    FavorsAndMastersState.SelectPage.FAVORS -> slideInHorizontally { -it } togetherWith
                            slideOutHorizontally { it }
                    FavorsAndMastersState.SelectPage.MASTERS -> slideInHorizontally { it } togetherWith
                        slideOutHorizontally { -it }
                }
            },
        ) { page ->
            when (page) {
                FavorsAndMastersState.SelectPage.FAVORS -> Column {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(12.dp)
                    )
                    actualState.favors.forEach {
                        FavorExpandableItem(
                            item = it,
                            modifier = Modifier
                                .fillMaxWidth(),
                            isExpanded = true,
                            style = FavorStyle.Compact,
                            selectedId = actualState.selectedFavorId,
                            onSelect = onFavorClick
                        )
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(12.dp)
                        )
                    }
                }
                FavorsAndMastersState.SelectPage.MASTERS -> Column {
                    Spacer(modifier = Modifier.height(12.dp))

                    VisifyTextCellItem(
                        text = "Любой мастер",
                        shape = RoundedCornerShape(6.dp),
                        hasDivider = false,
                        selectedState = actualState.isAnyMasterSelected.toSelectedStateWithGone(),
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .clickableNoInteraction(onAnyMasterClick)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OrganisationMastersItem(
                        category = null,
                        masters = actualState.masters,
                        rating = MasterRating.PROFESSION,
                        selectedId = actualState.selectedMasterId,
                        onMasterClick = onMasterClick,
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}