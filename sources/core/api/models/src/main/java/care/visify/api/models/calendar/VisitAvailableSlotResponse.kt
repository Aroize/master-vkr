package care.visify.api.models.calendar

import com.google.gson.annotations.SerializedName

data class VisitAvailableSlotResponse(
    @SerializedName("daytime")
    val daytime: String, //MORNING
    @SerializedName("isAvailable")
    val isAvailable: Boolean,
    @SerializedName("time")
    val time: String,
)