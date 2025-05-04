package care.visify.api.models.orders.market

import com.google.gson.annotations.SerializedName
import care.visify.api.models.common.ImageResponse
import care.visify.api.models.orders.OrderStatus
import java.time.LocalDateTime

data class ClientMarketOrderResponse(
    @SerializedName("id")
    val orderId: Int,
    @SerializedName("clientId")
    val clientId: Int,
    @SerializedName("selectedResponseId")
    val selectedResponseId: Int?,
    @SerializedName("details")
    val details: MarketOrderDetailsResponse,
    @SerializedName("filters")
    val filters: MarketOrderFiltersResponse,
    @SerializedName("status")
    val status: OrderStatus,
    @SerializedName("isActive")
    val isActive: Boolean
)