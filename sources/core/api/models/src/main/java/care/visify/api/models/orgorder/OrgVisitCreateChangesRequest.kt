package care.visify.api.models.orgorder


import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class OrgVisitCreateChangesRequest(
    @SerializedName("employeeId")
    val employeeId: Int,
    @SerializedName("favorId")
    val favorId: Int,
    @SerializedName("orderDttm")
    val orderDttm: LocalDateTime,
    @SerializedName("price")
    val price: Int,
    @SerializedName("durationMinutes")
    val durationMinutes: Int,
)