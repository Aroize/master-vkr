package care.visify.api.models.catalog

import com.google.gson.annotations.SerializedName

data class RatingsHistogramResponse(
    @SerializedName("avg")
    val average: Float,
    @SerializedName("total")
    val total: Int,
    @SerializedName("stars")
    val stars: List<Int>
)