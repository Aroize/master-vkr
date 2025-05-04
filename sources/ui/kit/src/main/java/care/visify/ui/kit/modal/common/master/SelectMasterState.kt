package care.visify.ui.kit.modal.common.master

import care.visify.ui.models.master.MasterUiModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class SelectMasterState(
    val masters: PersistentList<MasterUiModel>,
    val selectedMaster: MasterUiModel? = null,
) {

    val isAnySelected
        get() = selectedMaster != null

    companion object {
        val Empty = SelectMasterState(persistentListOf(), null)
    }
}