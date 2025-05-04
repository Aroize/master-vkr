package care.visify.api.models.notifications

import com.google.gson.annotations.SerializedName

class SettingsRequest(
    @SerializedName("entrypoint")
    val entrypoint: String,
    @SerializedName("durationInMinutes")
    val durationInMinutes: Int?
)