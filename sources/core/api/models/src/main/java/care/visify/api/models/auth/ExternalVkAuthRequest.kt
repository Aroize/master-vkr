package care.visify.api.models.auth

import com.google.gson.annotations.SerializedName

data class ExternalVkAuthRequest(
    @SerializedName("token")
    val token: String,
    @SerializedName("uuid")
    val uuid: String
)