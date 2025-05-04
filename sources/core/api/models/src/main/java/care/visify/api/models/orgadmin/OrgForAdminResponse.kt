package care.visify.api.models.orgadmin


import care.visify.api.models.common.ImageResponse
import com.google.gson.annotations.SerializedName
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime

data class OrgForAdminResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("addressId")
    val addressId: Int,
    @SerializedName("badges")
    val badges: List<String>,

    @SerializedName("favorsCount")
    val favorsCount: Int,
    @SerializedName("mastersCount")
    val mastersCount: Int,

    @SerializedName("avatarId")
    val avatarId: ImageResponse,
    @SerializedName("interiorPreviewIds")
    val interiorPreviewIds: List<ImageResponse>,
    @SerializedName("certificatesIds")
    val certificatesIds: List<ImageResponse>,

    @SerializedName("worktimes")
    val workTimes: List<WorkTime>,

    @SerializedName("moderationStatus")
    val moderationStatus: ModerationStatusResponse,
    @SerializedName("billingStatus")
    val billingStatusResponse: BillingStatusResponse,
)

data class BillingStatusResponse(
    @SerializedName("paidTill")
    val paidTill: LocalDateTime
)

data class WorkTime(
    @SerializedName("dayOfWeek")
    val dayOfWeek: DayOfWeek,
    @SerializedName("timeFrom")
    val timeFrom: LocalTime,
    @SerializedName("timeTill")
    val timeTill: LocalTime
)