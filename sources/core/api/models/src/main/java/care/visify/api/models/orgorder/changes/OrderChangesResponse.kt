package care.visify.api.models.orgorder.changes


import care.visify.api.models.orders.OrderChangesStatus
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class OrderChangesResponse(
    @SerializedName("id")
    val changesId: Int,
    @SerializedName("orderId")
    val orderId: Int,
    @SerializedName("responseId")
    val responseId: Int,

    @SerializedName("durationMinutesNew")
    val durationMinutesNew: Int,
    @SerializedName("durationMinutesOld")
    val durationMinutesOld: Int?,

    @SerializedName("employeeIdNew")
    val employeeIdNew: Int,
    @SerializedName("employeeIdOld")
    val employeeIdOld: Int?,

    @SerializedName("favorIdNew")
    val favorIdNew: Int,
    @SerializedName("favorIdOld")
    val favorIdOld: Int?,

    @SerializedName("priceNew")
    val priceNew: Int,
    @SerializedName("priceOld")
    val priceOld: Int?,


    @SerializedName("orderDttmNew")
    val orderDttmNew: LocalDateTime,
    @SerializedName("orderDttmOld")
    val orderDttmOld: LocalDateTime?,

    @SerializedName("changesStatus")
    val changesStatus: OrderChangesStatus,

    @SerializedName("orderStatusBeforeChange")
    val orderStatusBeforeChange: String,

    @SerializedName("createdByOrg")
    val createdByOrg: Boolean,
    @SerializedName("createdDttm")
    val createdDttm: LocalDateTime,
)