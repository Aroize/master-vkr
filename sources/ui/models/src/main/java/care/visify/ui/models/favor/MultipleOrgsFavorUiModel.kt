package care.visify.ui.models.favor

class MultipleOrgsFavorUiModel(
    val id: Int,
    val name: String,
    val price: String,
    val time: String,
    val orgTitle: String
) {
    fun toFavorUiModel() = FavorUiModel(
        id = id,
        name = name,
        price = price,
        time = time
    )
}