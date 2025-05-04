package care.visify.ui.kit.detailed.feedback

import androidx.compose.runtime.Composable
import care.visify.ui.kit.modal.VisifyModalBottomSheet
import care.visify.ui.kit.modal.VisifySheetState
import care.visify.ui.models.feedback.FeedbackUiModel

@Composable
fun FeedbackBottomSheet(state: VisifySheetState<FeedbackUiModel>) {
    VisifyModalBottomSheet(
        visifySheetState = state
    ) {
        ExtendedReviewItem(
            item = state.unsafeData,
        )
    }
}