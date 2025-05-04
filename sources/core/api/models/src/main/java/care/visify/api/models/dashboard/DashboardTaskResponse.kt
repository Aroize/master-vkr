package care.visify.api.models.dashboard


import care.visify.api.models.oneshot.BusinessOneShotTaskTypeResponse
import com.google.gson.annotations.SerializedName

data class DashboardTaskResponse(
    @SerializedName("createdTs")
    val createdTs: Long,
    @SerializedName("doneTs")
    val doneTs: Long?,
    @SerializedName("isHidden")
    val isHidden: Boolean,
    @SerializedName("orderId")
    val orderId: Int,
    @SerializedName("taskId")
    val taskId: Int,
    @SerializedName("taskType")
    val taskType: BusinessOneShotTaskTypeResponse,
)