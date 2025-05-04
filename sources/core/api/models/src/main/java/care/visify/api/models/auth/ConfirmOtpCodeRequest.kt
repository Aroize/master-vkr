package care.visify.api.models.auth

import com.google.gson.annotations.SerializedName

data class ConfirmOtpCodeRequest(
    @SerializedName("login")
    val login: String,
    @SerializedName("code")
    val code: Int
)