package care.visify.api.models.dashboard


import com.google.gson.annotations.SerializedName

data class OneShotTasksCountersResponse(
    @SerializedName("newReview")
    val newReview: Int?,
    @SerializedName("newMarketOrder")
    val newMarketOrder: Int?,
    @SerializedName("orderStatusChanged")
    val orderStatusChanged: Int?,
    @SerializedName("visitPhoto")
    val visitPhoto: Int?,
    @SerializedName("total")
    val total: Int,
    @SerializedName("hidden")
    val hidden: Int,
)