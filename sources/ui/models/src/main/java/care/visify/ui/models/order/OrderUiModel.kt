package care.visify.ui.models.order

import care.visify.core.image.Image
import care.visify.ui.models.favor.FavorUiModel
import care.visify.ui.models.feedback.FeedbackUiModel
import care.visify.ui.models.master.MasterUiModel

data class OrderUiModel(
    val id: Int,
    val covers: List<Image>,
    val feedback: FeedbackUiModel?,
    val master: MasterUiModel,
    val favor: FavorUiModel
)