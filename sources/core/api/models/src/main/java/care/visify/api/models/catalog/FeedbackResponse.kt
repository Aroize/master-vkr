package care.visify.api.models.catalog

import care.visify.api.models.common.ImageResponse
import com.google.gson.annotations.SerializedName

data class FeedbackResponse(
    @SerializedName("orderId")
    val orderId: Int,
    @SerializedName("review")
    val review: ReviewResponse,
    @SerializedName("wearing")
    val wearing: ReviewResponse?,
    @SerializedName("replyReview")
    val replyReview: ReplyResponse?,
    @SerializedName("replyWearing")
    val replyWearing: ReplyResponse?
) {
    data class ReviewResponse(
        @SerializedName("authorId")
        val authorId: Int,
        @SerializedName("text")
        val text: String,
        @SerializedName("photos")
        val photos: List<ImageResponse>,
        @SerializedName("rating")
        val rating: Float,
        @SerializedName("createdTs")
        val creation: Long
    )

    data class ReplyResponse(
        @SerializedName("authorId")
        val authorId: Int,
        @SerializedName("text")
        val text: String,
        @SerializedName("createdTs")
        val creation: Long
    )
}