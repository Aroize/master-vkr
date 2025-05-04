package care.visify.api.models.favor

import com.google.gson.annotations.SerializedName

data class FavorResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("categoryId")
    val categoryId: Int,
    @SerializedName("subcategoryId")
    val subcategoryId: Int,
    @SerializedName("orgId")
    val orgId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("minPrice")
    val minPrice: PriceResponse,
    @SerializedName("durationMinutes")
    val durationMinutes: Int
)