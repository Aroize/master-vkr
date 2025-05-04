package care.visify.api.models.orders.market

import care.visify.api.models.common.ImageResponse
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class MarketOrderDetailsResponse(
    @SerializedName("createDttm")
    val createDttm: LocalDateTime,
    @SerializedName("references")
    val references: List<ImageResponse>,
    @SerializedName("subcategoryId")
    val subcategoryId: Int,
    @SerializedName("orderComment")
    val orderComment: String?
)