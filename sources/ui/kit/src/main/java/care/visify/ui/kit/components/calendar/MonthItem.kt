package care.visify.ui.kit.components.calendar

import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import care.visify.ui.kit.R
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.mainTextPrimary
import care.visify.ui.kit.theme.values.mainTextTertiary
import care.visify.ui.kit.theme.values.mainTextWhite
import care.visify.ui.kit.theme.values.miniSecondary
import care.visify.ui.models.calendar.CalendarMonthUiModel
import care.visify.ui.models.calendar.SelectMode
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.PersistentSet
import kotlinx.collections.immutable.persistentSetOf
import kotlinx.collections.immutable.toPersistentList
import java.time.LocalDate
import kotlin.math.ceil


@Immutable
data class MonthUnits internal constructor(
    val insets: Dp = 20.dp,
) {
    companion object {
        @Composable
        fun units(): MonthUnits {
            return MonthUnits(20.dp)
        }
    }
}

@Immutable
data class MonthColors internal constructor(
    val enabledDayStyle: TextStyle,
    val disabledDayStyle: TextStyle,
    val selectedDayStyle: TextStyle,
    val selectedBoxColor: Color,
) {

    companion object {
        @Composable
        fun colors(size: TextUnit = 14.sp): MonthColors {
            return MonthColors(
                VisifyTheme.visifyTypography.mainTextPrimary.copy(fontSize = size),
                VisifyTheme.visifyTypography.mainTextTertiary.copy(fontSize = size),
                VisifyTheme.visifyTypography.mainTextWhite.copy(fontSize = size),
                VisifyTheme.colors.label.active
            )
        }
    }
}


//сам изменяет стейт и отдает новый готовый
class MultipleMonthValueChanger(
    private val states: PersistentList<MutableState<CalendarMonthUiModel>>,
    private val onChanged: (PersistentList<CalendarMonthUiModel>) -> Unit = { },
) : (CalendarMonthUiModel, PersistentSet<LocalDate>) -> Unit {
    override operator fun invoke(
        state: CalendarMonthUiModel,
        newSelectedDays: PersistentSet<LocalDate>,
    ) {
        val newState = state.copy(selectedDays = newSelectedDays)
        val other = states.filter { it.value.month != newState.month }
        other.forEach {
            it.value = it.value.copy(selectedDays = persistentSetOf())
        }
        val changed = requireNotNull(states.find { it.value.month == newState.month })
        changed.value = newState
        onChanged(states.map { it.value }.toPersistentList())
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MonthItem(
    monthModel: CalendarMonthUiModel,
    onValueChanged: (changedMonth: CalendarMonthUiModel, newSelectedDates: PersistentSet<LocalDate>) -> Unit,
    modifier: Modifier = Modifier,
    units: MonthUnits = MonthUnits.units(),
    colors: MonthColors = MonthColors.colors(),
) {

    val monthName = stringArrayResource(id = R.array.month_array)[monthModel.month.value - 1]

    var width by remember { mutableIntStateOf(0) }
    val textMeasurer = rememberTextMeasurer()
    val enabledTextStyle = remember { colors.enabledDayStyle }
    val disabledTextStyle = remember { colors.disabledDayStyle }
    val selectedTextStyle = remember { colors.selectedDayStyle }

    Column(
        modifier = modifier
    ) {
        Text(
            text = monthName,
            style = VisifyTheme.visifyTypography.miniSecondary,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        val ratio = 7f / ceil((monthModel.daysInMonth + monthModel.firstDayOfMonth) / 7f)
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .onSizeChanged { width = it.width }
                .aspectRatio(ratio)
                .pointerInteropFilter { event ->
                    val cellSize = width / 7
                    val row = (event.y / cellSize).toInt()
                    val col = (event.x / cellSize).toInt()
                    if (row == 0 && col < monthModel.firstDayOfMonth) return@pointerInteropFilter false
                    val day = row * 7 + col + 1 - monthModel.firstDayOfMonth
                    if (day > monthModel.daysInMonth) return@pointerInteropFilter false
                    if (monthModel.strategy
                            .isDayAvailable(day)
                            .not()
                    ) return@pointerInteropFilter false
                    if (event.action == MotionEvent.ACTION_UP) {
                        val dayLocalDate = LocalDate.of(
                            monthModel.date.year,
                            monthModel.month,
                            day
                        )
                        val selectedDays = when {
                            dayLocalDate in monthModel.selectedDays -> monthModel.selectedDays.remove(
                                dayLocalDate
                            )

                            monthModel.mode == SelectMode.SINGLE -> persistentSetOf(dayLocalDate)
                            else -> monthModel.selectedDays.add(dayLocalDate)
                        }
                        onValueChanged(monthModel, selectedDays)
                    }
                    true
                },
            onDraw = {
                repeat(monthModel.daysInMonth) { day ->
                    val dayWithOffset = monthModel.firstDayOfMonth + day
                    val row = dayWithOffset / 7
                    val col = dayWithOffset % 7
                    drawCell(
                        col,
                        row,
                        day + 1,
                        textMeasurer,
                        monthModel,
                        units,
                        enabledTextStyle,
                        disabledTextStyle,
                        selectedTextStyle,
                        colors
                    )
                }
            }
        )
    }
}

private fun DrawScope.drawCell(
    col: Int,
    row: Int,
    day: Int,
    textMeasurer: TextMeasurer,
    calendarMonthUiModel: CalendarMonthUiModel,
    units: MonthUnits,
    enabledTextStyle: TextStyle,
    disabledTextStyle: TextStyle,
    selectedTextStyle: TextStyle,
    colors: MonthColors,
) {
    val cellWidth = size.width / 7
    val x = col * cellWidth
    val y = (cellWidth * row) + cellWidth

    val text = day.toString()

    val measured = when {
        day in calendarMonthUiModel.selectedDays.map { it.dayOfMonth } -> {
            drawRoundRect(
                colors.selectedBoxColor,
                topLeft = Offset(x + units.insets.value, y - cellWidth + units.insets.value),
                size = Size(cellWidth - 2 * units.insets.value, cellWidth - 2 * units.insets.value),
                cornerRadius = CornerRadius(12.dp.value, 12.dp.value)
            )
            textMeasurer.measure(
                text,
                style = selectedTextStyle
            )
        }

        calendarMonthUiModel.strategy.isDayAvailable(day) -> textMeasurer.measure(
            text,
            style = enabledTextStyle
        )

        else -> textMeasurer.measure(
            text,
            style = disabledTextStyle
        )
    }

    val horizontal = (cellWidth - measured.size.width) / 2
    val vertical = (cellWidth - measured.size.height) / 2

    drawText(
        measured,
        topLeft = Offset(x + horizontal, (y - cellWidth) + vertical)
    )
}