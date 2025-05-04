package care.visify.api.models.chatting


import care.visify.api.models.common.ImageResponse
import care.visify.api.models.orders.OrderResponseStatus
import care.visify.api.models.orders.OrderStatus
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class MarketChatResponseResponse(
    @SerializedName("isMarketRequestRead")
    val isMarketRequestRead: Boolean,
    @SerializedName("isResponseRead")
    val isResponseRead: Boolean,
    @SerializedName("marketRequest")
    val marketRequest: MarketRequest,
    //todo орга в маркет ордере??? а если респонса нет? нул ?
    @SerializedName("orgId")
    val orgId: Int,
    @SerializedName("response")
    val marketResponse: MarketResponse?
)

data class MarketRequest(
    @SerializedName("createDttm")
    val createDttm: LocalDateTime,
    @SerializedName("employeeRateFrom")
    val employeeRateFrom: Float?,
    @SerializedName("employeeRateTill")
    val employeeRateTill: Float?,
    @SerializedName("orderComment")
    val orderComment: String?,
    @SerializedName("orderId")
    val orderId: Int,
    @SerializedName("orderTimeslots")
    val orderTimeslots: List<LocalDateTime>,
    @SerializedName("priceFrom")
    val priceFrom: Int,
    @SerializedName("priceTill")
    val priceTill: Int,
    @SerializedName("references")
    val references: List<ImageResponse>,
    @SerializedName("status")
    val status: OrderStatus
)

data class MarketResponse(
    @SerializedName("createDttm")
    val createDttm: LocalDateTime,
    @SerializedName("durationMinutes")
    val durationMinutes: Int,
    @SerializedName("employeeId")
    val employeeId: Int,
    @SerializedName("favorId")
    val favorId: Int,
    @SerializedName("orderDttm")
    val orderDttm: LocalDateTime,
    @SerializedName("orderId")
    val orderId: Int,
    @SerializedName("price")
    val price: Int,
    @SerializedName("priceCurrencyType")
    val priceCurrencyType: Int,
    @SerializedName("responseId")
    val responseId: Int,
    @SerializedName("responseStatus")
    val responseStatus: OrderResponseStatus
)