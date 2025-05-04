package care.visify.ui.kit.components.timeslots

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import care.visify.ui.kit.util.formatter.ConditionValidator
import care.visify.ui.models.timeslot.TimeSlotsState
import kotlinx.coroutines.launch

//todo НЕ РАБОТАЕТ
@Composable
fun TimeSlotsTrafficLight(
    state: TimeSlotsState,
    collectCanContinue: (Boolean) -> Unit,
) {
    val trafficLight = remember {
        ConditionValidator(3, mode = ConditionValidator.Mode.Any) { canContinue ->
            collectCanContinue(canContinue)
        }
    }

    val scope = rememberCoroutineScope()

    scope.launch {
        trafficLight.setLight(0, isGreen = state.morningSlots.any { it.selected })
    }
    scope.launch {
        trafficLight.setLight(1, isGreen = state.afternoonSlots.any { it.selected })
    }
    scope.launch {
        trafficLight.setLight(2, isGreen = state.eveningSlots.any { it.selected })
    }
}