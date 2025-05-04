package care.visify.api.models.master

import com.google.gson.annotations.SerializedName
import care.visify.api.models.common.ImageResponse

class AboutMasterResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("badges")
    val badges: List<String>,
    @SerializedName("categories")
    val categories: List<String>,
    @SerializedName("certificates")
    val certificates: List<ImageResponse>
)