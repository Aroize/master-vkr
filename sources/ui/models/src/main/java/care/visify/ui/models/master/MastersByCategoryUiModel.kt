package care.visify.ui.models.master

import kotlinx.collections.immutable.PersistentList


data class MastersByCategoryUiModel(
    val category: String,
    val masters: PersistentList<MasterWithFavorsUiModel>
)