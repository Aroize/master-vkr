package care.visify.api.models.orgorder

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class AddPhotosToVisitRequest(
    @SerializedName("photosIds")
    val photosIds: List<UUID>,
)