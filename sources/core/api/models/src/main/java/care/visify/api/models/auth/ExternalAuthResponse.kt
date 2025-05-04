package care.visify.api.models.auth

import com.google.gson.annotations.SerializedName

data class ExternalAuthResponse(
    @SerializedName("externalAuth")
    val externalAuth: VkExternalAuthResponse,
    @SerializedName("tokens")
    val tokens: TokensResponse
)