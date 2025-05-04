package care.visify.api.models.visits

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.util.UUID

class EditVisitRequest(
    @SerializedName("employeeId")
    val employeeId: Int,
    @SerializedName("favorId")
    val favorId: Int,
    @SerializedName("orderDttm")
    val orderDttm: LocalDateTime,
    @SerializedName("referencesIds")
    val references: List<UUID>
)