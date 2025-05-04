package care.visify.api.models.error

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("errorCode")
    val errorCode: Int,
    @SerializedName("message")
    val message: String
)