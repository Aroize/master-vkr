package care.visify.api.models.catalog

import com.google.gson.annotations.SerializedName
import care.visify.api.models.common.ImageResponse

data class AboutOrgResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("badges")
    val badges: List<String>,
    @SerializedName("description")
    val description: String,
    @SerializedName("certificates")
    val certificates: List<ImageResponse>,
    @SerializedName("address")
    val address: Address,
    @SerializedName("openTill")
    val openTill: String?,
    @SerializedName("openFrom")
    val openFrom: String?,
) {
    data class Address(
        val name: String,
        val lat: Float,
        val lon: Float
    )
}