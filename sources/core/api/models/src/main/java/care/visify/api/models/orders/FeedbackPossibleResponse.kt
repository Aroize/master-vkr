package care.visify.api.models.orders

import com.google.gson.annotations.SerializedName

class FeedbackPossibleResponse(
    @SerializedName("review")
    val review: Boolean,
    @SerializedName("wearing")
    val wearing: Boolean
)