package care.visify.api.models.catalog

import com.google.gson.annotations.SerializedName

data class OrganisationGeoResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("lat")
    val lat: Float,
    @SerializedName("lon")
    val lon: Float
)