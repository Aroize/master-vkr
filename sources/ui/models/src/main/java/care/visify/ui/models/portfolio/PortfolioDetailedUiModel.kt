package care.visify.ui.models.portfolio

import care.visify.core.image.Image
import care.visify.ui.models.feedback.FeedbackUiModel
import care.visify.ui.models.master.MasterUiModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf


data class PortfolioDetailedUiModel(
    val images: PersistentList<Image>,
    val favor: String,
    val reviewItem: FeedbackUiModel?,
    val master: MasterUiModel
) {
    companion object {
        val Stub = PortfolioDetailedUiModel(
            images = persistentListOf(),
            favor = "",
            reviewItem = null,
            master = MasterUiModel(0, "", null, 0f, "", Image())
        )
    }
}