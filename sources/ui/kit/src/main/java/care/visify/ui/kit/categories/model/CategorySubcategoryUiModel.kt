package care.visify.ui.kit.categories.model


//fixme (wrong subcategory)
// looks like this: subCategory = category.subcategories.firstOrNull()?.name
data class CategorySubcategoryUiModel(
    val category: String,
    val subCategory: String?,
) {
    val uiString
        get() = subCategory?.let { "$category, ${it.lowercase()}" } ?: category

    companion object {
        fun stubFull() = CategorySubcategoryUiModel(
            category = "Массаж",
            subCategory = "спина"
        )

        fun stubNoSubcategory() = CategorySubcategoryUiModel(
            category = "Массаж",
            subCategory = null
        )
    }
}