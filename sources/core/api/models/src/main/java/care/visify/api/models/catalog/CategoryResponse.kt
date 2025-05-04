package care.visify.api.models.catalog

import com.google.gson.annotations.SerializedName
import care.visify.api.models.common.ImageResponse

data class CategoryResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("icon")
    val icon: ImageResponse,
    @SerializedName("subcategories")
    val subcategories: List<Subcategory>
) {
    data class Subcategory(
        @SerializedName("id")
        val id: Int,
        @SerializedName("categoryId")
        val categoryId: Int,
        @SerializedName("name")
        val name: String
    )
}