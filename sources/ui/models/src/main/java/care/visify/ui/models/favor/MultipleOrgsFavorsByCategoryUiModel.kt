package care.visify.ui.models.favor

class MultipleOrgsFavorsByCategoryUiModel(
    val category: String,
    val favors: List<ConflictingFavorsUiModel>
)