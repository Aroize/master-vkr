package care.visify.api.models.calendar

import com.google.gson.annotations.SerializedName

data class VisitAvailableDateResponse(
    @SerializedName("date")
    val date: String,
    @SerializedName("isAvailable")
    val isAvailable: Boolean
)