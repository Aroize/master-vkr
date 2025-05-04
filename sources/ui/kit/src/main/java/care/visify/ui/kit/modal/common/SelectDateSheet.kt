package care.visify.ui.kit.modal.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.R
import care.visify.ui.kit.components.calendar.MonthItem
import care.visify.ui.kit.modal.VisifyModalBottomSheet
import care.visify.ui.kit.modal.VisifySheetState
import care.visify.ui.kit.modal.footers.SelectModalFooter
import care.visify.ui.kit.modal.footers.WideButtonFooter
import care.visify.ui.kit.modal.headers.VisifyModalHeader
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.util.asString
import care.visify.ui.models.calendar.CalendarMonthUiModel
import care.visify.ui.models.calendar.SelectDateState
import kotlinx.collections.immutable.PersistentSet
import java.time.LocalDate


@Composable
fun SelectDateSheet(
    state: VisifySheetState<SelectDateState>,
    title : String = stringResource(R.string.bottom_sheet_date_title),
    newSelectFooter: Boolean = false,
    onDateClick: (changedMonth: CalendarMonthUiModel, newSelectedDates: PersistentSet<LocalDate>) -> Unit,
    onSelectClick: () -> Unit = {},
    onDismissAction: () -> Unit = {},
) {
    val actualState = state.data ?: return

    VisifyModalBottomSheet(
        visifySheetState = state,
        header = {
            VisifyModalHeader(titleText = title)
        },
        footer = {
            Column {
                AnimatedVisibility(
                    actualState.desc != null,
                    enter = expandVertically() + slideInHorizontally(initialOffsetX = { -it }),
                    exit = shrinkVertically() + slideOutHorizontally(targetOffsetX = { it }),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(VisifyTheme.colors.frame.infoYellow)
                    ) {
                        Text(
                            text = actualState.desc?.asString().orEmpty(),
                            style = VisifyTheme.visifyTypography.mini.copy(
                                color = VisifyTheme.colors.frame.black
                            ),
                            modifier = Modifier.padding(VisifyTheme.padding._16dp)
                        )
                    }
                }
                if (newSelectFooter) {
                    WideButtonFooter(
                        label = "Выбрать",
                        enabled = actualState.months.any { it.selectedDays.isNotEmpty() },
                        onClick = onSelectClick
                    )
                } else {
                    SelectModalFooter(
                        actualState.months.any { it.selectedDays.isNotEmpty() },
                        onSelectClick
                    )
                }
            }
        },
        onDismiss = onDismissAction,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            actualState.months.forEach {
                MonthItem(
                    monthModel = it,
                    onValueChanged = onDateClick,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}