package care.visify.ui.models.favor

class ConflictingFavorsUiModel(
    val name: String,
    val priceFrom: String,
    val favors: List<MultipleOrgsFavorUiModel>
) {

    val isConflicting: Boolean = favors.size > 1

    fun toFavorUiModel(): FavorUiModel {
        assert(isConflicting.not())
        return favors.first().toFavorUiModel()
    }
}