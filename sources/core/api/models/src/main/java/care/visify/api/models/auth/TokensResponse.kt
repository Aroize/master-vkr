package care.visify.api.models.auth

import com.google.gson.annotations.SerializedName

data class TokensResponse(
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String
)