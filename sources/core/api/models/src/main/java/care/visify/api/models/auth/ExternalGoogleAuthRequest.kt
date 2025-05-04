package care.visify.api.models.auth

import com.google.gson.annotations.SerializedName

data class ExternalGoogleAuthRequest(
    @SerializedName("token")
    val idToken: String
)