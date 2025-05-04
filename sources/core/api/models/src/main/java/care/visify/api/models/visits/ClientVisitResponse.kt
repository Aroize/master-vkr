package care.visify.api.models.visits

import com.google.gson.annotations.SerializedName
import care.visify.api.models.common.ImageResponse
import care.visify.api.models.orders.OrderStatus
import care.visify.api.models.orders.OrderType
import java.time.LocalDateTime


data class ClientVisitResponse(
    @SerializedName("orderId")
    val visitId: Int,
    @SerializedName("clientId")
    val clientId: Int,
    @SerializedName("employeeId")
    val masterId: Int,
    @SerializedName("orgId")
    val orgId: Int,
    @SerializedName("favorId")
    val favorId: Int,
    @SerializedName("responseId")
    val responseId: Int,
    @SerializedName("createDttm")
    val createDttm: LocalDateTime,
    @SerializedName("orderDttm")
    val orderDttm: LocalDateTime,
    @SerializedName("price")
    val price: Int,
    @SerializedName("priceCurrencyType")
    val princeCurrencyType: Int,
    @SerializedName("durationMinutes")
    val durationMinutes: Int,
    @SerializedName("references")
    val references: List<ImageResponse>,
    @SerializedName("orderComment")
    val comment: String?,
    @SerializedName("isActive")
    val isActive: Boolean,
    @SerializedName("status")
    val status: OrderStatus,
    @SerializedName("type")
    val type: OrderType
)