package care.visify.api.models.orgorder.respond


import care.visify.api.models.orders.OrderResponseStatus
import com.google.gson.annotations.SerializedName

data class OrgMarketOrderRespondResponse(
    @SerializedName("id")
    val responseId: Int,
    @SerializedName("requestId")
    val requestId: Int,
    @SerializedName("status")
    val status: OrderResponseStatus
)