package care.visify.api.models.common

import com.google.gson.annotations.SerializedName
import java.util.UUID

class ImageResponse(
    @SerializedName("id")
    val id: UUID,
    @SerializedName("width")
    val width: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("preview")
    val preview: String
)