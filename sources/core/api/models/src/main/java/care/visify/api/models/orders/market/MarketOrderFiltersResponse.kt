package care.visify.api.models.orders.market

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class MarketOrderFiltersResponse(
    @SerializedName("orderTimeslots")
    val orderTimeslots: List<LocalDateTime>,
    @SerializedName("priceFrom")
    val priceFrom: Int?,
    @SerializedName("priceTo")
    val priceTo: Int?,
    @SerializedName("employeeRatingFrom")
    val employeeRatingFrom: Float?,
    @SerializedName("employeeRatingTo")
    val employeeRatingTo: Float?
)