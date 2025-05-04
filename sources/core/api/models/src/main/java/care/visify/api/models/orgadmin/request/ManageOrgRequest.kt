package care.visify.api.models.orgadmin.request


import care.visify.api.models.orgadmin.WorkTime
import com.google.gson.annotations.SerializedName
import java.util.UUID

data class ManageOrgRequest(
    @SerializedName("addressId")
    val addressId: Int,
    @SerializedName("avatarId")
    val avatarId: UUID,
    @SerializedName("badges")
    val badges: List<String>,
    @SerializedName("certificatesIds")
    val certificatesIds: List<UUID>,
    @SerializedName("description")
    val description: String,
    @SerializedName("interiorPreviewIds")
    val interiorPreviewIds: List<UUID>,
    @SerializedName("name")
    val name: String,
    @SerializedName("worktimes")
    val workTimes: List<WorkTime>
)