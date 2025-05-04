package care.visify.api.models.favor

import com.google.gson.annotations.SerializedName

data class PriceResponse(
    @SerializedName("price")
    val price: Int,
    @SerializedName("priceCurrencyType")
    val priceType: Int
)