package care.visify.api.models.timesheet

import care.visify.api.models.common.ImageResponse
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime


data class TimesheetRecordResponse(
    @SerializedName("dttmFrom")
    val dttmFrom: LocalDateTime,
    @SerializedName("dttmTill")
    val dttmTill: LocalDateTime,
    @SerializedName("orderId")
    val orderId: Int,
    @SerializedName("clientId")
    val clientId: Int,
    @SerializedName("employeeId")
    val employeeId: Int,
    @SerializedName("photos")
    val photos: List<ImageResponse>,
    @SerializedName("slotStatus")
    val slotStatus: TimesheetOrderStatus
)

