package care.visify.api.models.orders

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.util.UUID


data class CreateMarketOrderRequest(
    @SerializedName("cityId")
    val cityId: Int,
    @SerializedName("subcategoryId")
    val subcategoryId: Int,
    @SerializedName("orderTimeslots")
    val orderTimeslots: List<LocalDateTime>,
    @SerializedName("priceFrom")
    val priceFrom: Int,
    @SerializedName("priceTo")
    val priceTo: Int,
    @SerializedName("employeeRatingFrom")
    val masterRatingFrom: Float,
    @SerializedName("employeeRatingTo")
    val masterRatingTo: Float,
    @SerializedName("orderComment")
    val orderComment: String?,
    @SerializedName("photoReferencesIds")
    val photoReferencesIds: List<UUID>,
)