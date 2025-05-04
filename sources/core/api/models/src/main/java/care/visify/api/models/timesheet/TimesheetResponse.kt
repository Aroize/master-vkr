package care.visify.api.models.timesheet

import care.visify.api.models.common.CollectionResponse
import com.google.gson.annotations.SerializedName
import java.time.LocalTime

data class TimesheetResponse(
    @SerializedName("openFrom")
    val openFrom: LocalTime,
    @SerializedName("openTill")
    val openTill: LocalTime,
    @SerializedName("orderSlots")
    val orderSlots: CollectionResponse<TimesheetRecordResponse>
)