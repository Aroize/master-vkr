package care.visify.api.models.master

import com.google.gson.annotations.SerializedName
import care.visify.api.models.catalog.RatingModel
import care.visify.api.models.common.ImageResponse

data class MasterResponse(
    @SerializedName("employeeId")
    val id: Int,
    @SerializedName("avatar")
    val avatar: ImageResponse,
    @SerializedName("name")
    val name: String,
    @SerializedName("surname")
    val surname: String?,
    @SerializedName("role")
    val role: String,
    @SerializedName("rating")
    val rating: RatingModel,
    @SerializedName("isFavourite")
    val isFavourite: Boolean
)