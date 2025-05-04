package care.visify.api.models.auth

import com.google.gson.annotations.SerializedName

data class VkExternalAuthResponse(
    @SerializedName("userId")
    val userId: Long,
    @SerializedName("access")
    val access: String
)