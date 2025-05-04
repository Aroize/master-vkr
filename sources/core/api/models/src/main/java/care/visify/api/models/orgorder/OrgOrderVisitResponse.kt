package care.visify.api.models.orgorder


import care.visify.api.models.common.ImageResponse
import care.visify.api.models.orders.OrderStatus
import care.visify.api.models.orders.OrderType
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class OrgOrderVisitResponse(
    @SerializedName("clientId")
    val clientId: Int,
    @SerializedName("createDttm")
    val createDttm: LocalDateTime,
    @SerializedName("durationMinutes")
    val durationMinutes: Int,
    @SerializedName("employeeId")
    val employeeId: Int,
    @SerializedName("favorId")
    val favorId: Int,
    @SerializedName("isActive")
    val isActive: Boolean,
    @SerializedName("orderComment")
    val comment: String,
    @SerializedName("orderDttm")
    val orderDttm: LocalDateTime,
    @SerializedName("orderId")
    val visitId: Int,
    @SerializedName("orgId")
    val orgId: Int,
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("priceCurrencyType")
    val priceCurrencyType: Int,
    @SerializedName("references")
    val references: List<ImageResponse>,
    @SerializedName("responseId")
    val responseId: Int,
    @SerializedName("status")
    val status: OrderStatus,
    @SerializedName("subcategoryId")
    val subcategoryId: Int,
    @SerializedName("type")
    val type: OrderType
)