package care.visify.ui.models.master

import care.visify.ui.models.favor.FavorUiModel
import care.visify.ui.models.master.MasterUiModel

data class MasterWithFavorsUiModel(
    val master: MasterUiModel,
    val favors: List<FavorUiModel>
)