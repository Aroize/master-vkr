package care.visify.api.models.orgadmin

import com.google.gson.annotations.SerializedName

data class ModerationStatusResponse(
    @SerializedName("status")
    val status: ModerationStatus,
    @SerializedName("comment")
    val comment: String?,
)

enum class ModerationStatus {
    IN_PROGRESS, PASSED, HAS_COMMENT, BLOCKED,
}