package care.visify.api.models.orders.market


import care.visify.api.models.orders.OrderStatus
import com.google.gson.annotations.SerializedName

data class OrgMarketOrderResponse(
    @SerializedName("clientId")
    val clientId: Int,
    @SerializedName("details")
    val details: MarketOrderDetailsResponse,
    @SerializedName("filters")
    val filters: MarketOrderFiltersResponse,
    @SerializedName("id")
    val id: Int,
    @SerializedName("isActive")
    val isActive: Boolean,
    @SerializedName("status")
    val status: OrderStatus
)