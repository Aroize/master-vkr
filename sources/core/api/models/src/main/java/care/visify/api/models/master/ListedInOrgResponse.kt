package care.visify.api.models.master

import com.google.gson.annotations.SerializedName

class ListedInOrgResponse(
    @SerializedName("orgs")
    val orgs: List<OrgId>
) {
    data class OrgId(@SerializedName("id") val id: Int)
}