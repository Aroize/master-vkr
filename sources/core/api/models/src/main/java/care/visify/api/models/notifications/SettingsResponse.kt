package care.visify.api.models.notifications

import com.google.gson.annotations.SerializedName

class SettingsResponse(
    @SerializedName("enabled")
    val enabled: Boolean,
    @SerializedName("durationInMinutes")
    val durationInMinutes: Int?
)