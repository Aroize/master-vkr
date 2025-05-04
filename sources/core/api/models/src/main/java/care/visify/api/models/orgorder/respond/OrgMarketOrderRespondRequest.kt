package care.visify.api.models.orgorder.respond


import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class OrgMarketOrderRespondRequest(
    @SerializedName("durationMinutes")
    val durationMinutes: Int,
    @SerializedName("employeeId")
    val employeeId: Int,
    @SerializedName("favorId")
    val favorId: Int,
    @SerializedName("orderDttm")
    val orderDttm: LocalDateTime,
    @SerializedName("price")
    val price: Int,
    @SerializedName("priceCurrencyType")
    val priceCurrencyType: Int
)