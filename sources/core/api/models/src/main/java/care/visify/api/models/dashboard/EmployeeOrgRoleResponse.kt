package care.visify.api.models.dashboard


import com.google.gson.annotations.SerializedName

data class EmployeeOrgRoleResponse(
    @SerializedName("orgId")
    val orgId: Int,
    @SerializedName("isAdmin")
    val isAdmin: Boolean,
    @SerializedName("isMaster")
    val isMaster: Boolean,
    @SerializedName("role")
    val role: String
)