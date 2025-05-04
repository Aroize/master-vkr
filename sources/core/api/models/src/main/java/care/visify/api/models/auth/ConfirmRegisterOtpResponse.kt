package care.visify.api.models.auth

import com.google.gson.annotations.SerializedName

data class ConfirmRegisterOtpResponse(
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("secret")
    val secret: String
)