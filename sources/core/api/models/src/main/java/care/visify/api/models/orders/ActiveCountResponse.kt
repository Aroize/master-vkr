package care.visify.api.models.orders

import com.google.gson.annotations.SerializedName

class ActiveCountResponse(
    @SerializedName("activeOrders")
    val activeOrders: Int
)