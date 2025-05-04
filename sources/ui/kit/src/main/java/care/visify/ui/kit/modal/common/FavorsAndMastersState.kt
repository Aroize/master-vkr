package care.visify.ui.kit.modal.common

import care.visify.ui.models.favor.FavorsByCategoryUiModel
import care.visify.ui.models.master.MasterUiModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class FavorsAndMastersState(
    val page: SelectPage = SelectPage.FAVORS,
    val favors: PersistentList<FavorsByCategoryUiModel> = persistentListOf(),
    val masters: PersistentList<MasterUiModel> = persistentListOf(),
    val selectedFavorId: Int? = null,
    val selectedMasterId: Int? = null,
    val isAnyMasterSelected: Boolean = false,
) {
    enum class SelectPage {
        FAVORS, MASTERS
    }

    companion object {

        val Empty: FavorsAndMastersState =
            FavorsAndMastersState()

        fun create(
            favors: PersistentList<FavorsByCategoryUiModel>,
            masters: PersistentList<MasterUiModel>,
        ): FavorsAndMastersState = FavorsAndMastersState(
            favors = favors,
            masters = masters
        )
    }
}