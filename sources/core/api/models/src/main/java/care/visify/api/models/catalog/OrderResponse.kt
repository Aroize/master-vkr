package care.visify.api.models.catalog

import com.google.gson.annotations.SerializedName
import care.visify.api.models.common.ImageResponse
import java.time.LocalDateTime

data class OrderResponse(
    @SerializedName("orderId")
    val id: Int,
    @SerializedName("orgId")
    val orgId: Int,
    @SerializedName("feedback")
    val feedback: FeedbackResponse?,
    @SerializedName("favorCategoryId")
    val favorCategoryId: Int,
    @SerializedName("employeeId")
    val masterId: Int,
    @SerializedName("favorId")
    val favorId: Int,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("orderDttm")
    val orderDttm: LocalDateTime,
    @SerializedName("photos")
    val photos: List<ImageResponse>
)