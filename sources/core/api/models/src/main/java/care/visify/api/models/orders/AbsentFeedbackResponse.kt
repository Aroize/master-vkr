package care.visify.api.models.orders

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class AbsentFeedbackResponse(
    @SerializedName("orderId")
    val orderId: Int,
    @SerializedName("favorId")
    val favorId: Int,
    @SerializedName("employeeId")
    val masterId: Int,
    @SerializedName("orderDttm")
    val orderDttm: LocalDateTime
)