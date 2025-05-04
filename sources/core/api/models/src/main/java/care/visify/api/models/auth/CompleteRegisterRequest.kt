package care.visify.api.models.auth

import com.google.gson.annotations.SerializedName

data class CompleteRegisterRequest(
    @SerializedName("login")
    val login: String,
    @SerializedName("secret")
    val secret: String,
    @SerializedName("name")
    val name: String
)