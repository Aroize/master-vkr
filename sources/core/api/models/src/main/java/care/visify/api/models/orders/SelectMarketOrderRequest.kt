package care.visify.api.models.orders

import com.google.gson.annotations.SerializedName

data class SelectMarketOrderRequest(
    @SerializedName("phoneNumber")
    val phoneNumber : String,
)