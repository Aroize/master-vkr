package care.visify.ui.models.favor

import kotlinx.collections.immutable.PersistentList


data class FavorsByCategoryUiModel(
    val category: String,
    val favors: PersistentList<FavorUiModel>
)