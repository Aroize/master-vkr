package care.visify.ui.kit.modal.common

import care.visify.ui.models.favor.FavorsByCategoryUiModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class CategoryFavorState(
    val favors: PersistentList<FavorsByCategoryUiModel> = persistentListOf(),
    val selectedFavorId: Int? = null,
) {
    companion object {
        val Empty = CategoryFavorState()

        fun create(
            favors: PersistentList<FavorsByCategoryUiModel>,
        ): CategoryFavorState = CategoryFavorState(
            favors = favors,
            selectedFavorId = null
        )
    }
}