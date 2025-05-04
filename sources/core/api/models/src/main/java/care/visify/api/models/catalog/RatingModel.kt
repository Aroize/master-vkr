package care.visify.api.models.catalog

import com.google.gson.annotations.SerializedName


data class RatingModel(
    @SerializedName("average")
    val average: Float,
    @SerializedName("total")
    val total: Int
)