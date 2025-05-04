package care.visify.api.models.visits

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.util.UUID

class CreateVisitRequest(
    @SerializedName("employeeId")
    val employeeId: Int,
    @SerializedName("orgId")
    val orgId: Int,
    @SerializedName("favorId")
    val favorId: Int,
    @SerializedName("orderDttm")
    val orderDttm: LocalDateTime,
    @SerializedName("orderComment")
    val orderComment: String?,
    @SerializedName("photoReferencesIds")
    val photoReferencesIds: List<UUID>,
    @SerializedName("phoneNumber")
    val phoneNumber: String
)