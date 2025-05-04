package care.visify.api.models.profile

import com.google.gson.annotations.SerializedName
import care.visify.api.models.common.ImageResponse

data class UserProfileResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("surname")
    val surname: String?,
    @SerializedName("avatar")
    val avatar: ImageResponse,
    @SerializedName("email")
    val email: String?,
    @SerializedName("phoneNumber")
    val phoneNumber: String?
)