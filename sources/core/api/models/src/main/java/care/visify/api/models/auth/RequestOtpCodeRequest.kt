package care.visify.api.models.auth

import com.google.gson.annotations.SerializedName

data class RequestOtpCodeRequest(
    @SerializedName("login")
    val login: String
)