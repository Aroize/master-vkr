package care.visify.api.models.catalog

import com.google.gson.annotations.SerializedName
import care.visify.api.models.common.ImageResponse

data class OrganisationResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("logo")
    val logo: ImageResponse,
    @SerializedName("categories")
    val categories: List<Int>,
    @SerializedName("description")
    val description: String?,
    @SerializedName("rating")
    val rating: RatingModel,
    @SerializedName("previews")
    val previews: List<ImageResponse>,
    @SerializedName("price")
    val price: Int,
    @SerializedName("addressText")
    val addressText: String,
    @SerializedName("favouriteCommentText")
    val favouriteCommentText: String?,
    @SerializedName("openTill")
    val openTill: String?,
    @SerializedName("openFrom")
    val openFrom: String?,
    @SerializedName("isFavourite")
    val isFavourite: Boolean
)