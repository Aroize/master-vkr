package care.visify.ui.kit.detailed.master

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.containers.VisifyColumn
import care.visify.ui.kit.containers.cellitem.toSelectedStateWithGone
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.subheaderPrimary
import care.visify.ui.kit.util.cellShape
import care.visify.ui.kit.util.clickableNoInteraction
import care.visify.ui.models.master.MasterUiModel

@Composable
fun OrganisationMastersItem(
    category: String?,
    masters: List<MasterUiModel>,
    rating: MasterRating = MasterRating.END,
    selectedId: Int? = null,
    onMasterClick: (Int) -> Unit
) {
    VisifyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = VisifyTheme.padding._16dp)
    ) {
        category?.let {
            Text(
                text = it,
                style = VisifyTheme.visifyTypography.subheaderPrimary,
                modifier = Modifier.padding(start = 16.dp, top = 20.dp, bottom = 8.dp)
            )
        }

        masters.forEachIndexed { index, master ->
            MasterItem(
                master = master,
                shape = cellShape(masters, index, 6.dp),
                hasDivider = index != masters.lastIndex,
                selectedState = (selectedId == master.id).toSelectedStateWithGone(),
                masterRating = rating,
                modifier = Modifier
                    .heightIn(min = 64.dp)
                    .clickableNoInteraction {
                        onMasterClick(master.id)
                    },
            )
        }
    }
}