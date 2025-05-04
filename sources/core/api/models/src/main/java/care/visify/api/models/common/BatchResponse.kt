package care.visify.api.models.common

import com.google.gson.annotations.SerializedName

data class BatchResponse<T>(
    @SerializedName("items")
    val items: List<T>,
    @SerializedName("missedIds")
    val missedIds: List<Int>
)