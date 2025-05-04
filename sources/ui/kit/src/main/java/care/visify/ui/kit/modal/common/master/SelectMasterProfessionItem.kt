package care.visify.ui.kit.modal.common.master

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import care.visify.ui.kit.master.MasterProfessionItem
import care.visify.ui.models.master.MasterUiModel

@Composable
fun SelectMasterProfessionItem(
    modifier: Modifier = Modifier,
    master: MasterUiModel,
    shape: Shape,
    hasDivider: Boolean,
    isSelected: Boolean,
    onMasterClick: (master: MasterUiModel) -> Unit,
) {
    MasterProfessionItem(
        modifier = modifier,
        masterName = master.name,
        masterSurname = master.surname,
        avatar = master.avatar,
        profession = master.profession,
        rating = master.rating,
        favorPrice = null,
        shape = shape,
        isSelected = isSelected,
        hasDivider = hasDivider,
        onClick = { onMasterClick(master) }
    )
}