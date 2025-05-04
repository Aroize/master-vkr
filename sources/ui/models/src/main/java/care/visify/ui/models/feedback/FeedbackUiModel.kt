package care.visify.ui.models.feedback

import care.visify.core.image.Image
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import java.time.LocalDateTime

data class FeedbackUiModel(
    val orderId: Int,
    val date: LocalDateTime,
    val author: String,
    val review: ReviewUiModel,
    val wearing: ReviewUiModel?,
    val organisationReply: String?,
    val organisationWearingReply: String?
) {
    data class ReviewUiModel(
        val text: String,
        val date: LocalDateTime,
        val images: PersistentList<Image>,
        val rating: Float
    )

    companion object {
        val Stub = FeedbackUiModel(
            orderId = 0,
            date = LocalDateTime.now(),
            author = "",
            review = ReviewUiModel(text = "", date = LocalDateTime.now(), images = persistentListOf(), rating = 0f),
            wearing = null,
            organisationReply = null,
            organisationWearingReply = null
        )
    }
}