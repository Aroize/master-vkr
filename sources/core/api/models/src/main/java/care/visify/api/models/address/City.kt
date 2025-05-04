package care.visify.api.models.address

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("lat")
    val lat: Float,
    @SerializedName("lon")
    val lon: Float
)