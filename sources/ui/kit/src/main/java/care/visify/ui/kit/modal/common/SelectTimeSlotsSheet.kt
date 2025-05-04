package care.visify.ui.kit.modal.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.components.timeslots.TimeSlotRowList
import care.visify.ui.kit.modal.VisifyModalBottomSheet
import care.visify.ui.kit.modal.VisifySheetState
import care.visify.ui.kit.modal.footers.SelectModalFooter
import care.visify.ui.kit.modal.footers.WideButtonFooter
import care.visify.ui.kit.modal.headers.VisifyModalHeader
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.mainTextPrimary
import care.visify.ui.models.timeslot.TimeSlotUiModel
import care.visify.ui.models.timeslot.TimeSlotsState

@Composable
fun SelectTimeSlotsSheet(
    state: VisifySheetState<TimeSlotsState>,
    title : String = "Время",
    //todo
    newSelectFooter: Boolean = false,
    onTimeSlotClick: (TimeSlotUiModel) -> Unit,
    onSelectClick: () -> Unit = {},
    onDismiss: () -> Unit = {},
) {

    val actualState = state.data ?: return

    VisifyModalBottomSheet(
        visifySheetState = state,
        header = {
            VisifyModalHeader(titleText = title)
        },
        footer = {
            if (newSelectFooter) {
                WideButtonFooter(
                    label = "Выбрать",
                    enabled = actualState.isAnySelected(),
                    onClick = onSelectClick
                )
            } else {
                SelectModalFooter(actualState.isAnySelected(), onSelectClick)
            }
        },
        onDismiss = onDismiss,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if (actualState.isAnyAvailable) {
                if (actualState.morningSlots.isNotEmpty()) {
                    TimeSlotRowList(
                        title = "Утро",
                        timeSlotsList = actualState.morningSlots,
                        onClick = onTimeSlotClick
                    )
                }
                if (actualState.afternoonSlots.isNotEmpty()) {
                    TimeSlotRowList(
                        title = "День",
                        timeSlotsList = actualState.afternoonSlots,
                        onClick = onTimeSlotClick
                    )
                }
                if (actualState.eveningSlots.isNotEmpty()) {
                    TimeSlotRowList(
                        title = "Вечер",
                        timeSlotsList = actualState.eveningSlots,
                        onClick = onTimeSlotClick
                    )
                }
                Spacer(Modifier.height(8.dp))
            } else {
                Text(
                    "Нет доступных слотов",
                    style = VisifyTheme.visifyTypography.mainTextPrimary,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}