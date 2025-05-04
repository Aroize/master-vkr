package care.visify.api.models.catalog

import com.google.gson.annotations.SerializedName
import care.visify.api.models.common.CollectionResponse
import care.visify.api.models.master.MasterResponse

data class SearchResponse(
    @SerializedName("masters")
    val masters: CollectionResponse<MasterResponse>,
    @SerializedName("orgs")
    val orgs: CollectionResponse<OrganisationResponse>
)