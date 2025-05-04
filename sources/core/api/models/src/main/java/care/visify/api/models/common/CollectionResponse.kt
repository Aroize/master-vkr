package care.visify.api.models.common

import com.google.gson.annotations.SerializedName

data class CollectionResponse<T>(
    @SerializedName("items")
    val items: List<T>,
    @SerializedName("total")
    val totalItems: Int
)