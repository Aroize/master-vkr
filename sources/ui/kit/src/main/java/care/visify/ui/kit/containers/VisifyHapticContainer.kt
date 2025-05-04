package care.visify.ui.kit.containers

import android.content.Context
import android.view.HapticFeedbackConstants
import android.view.View
import android.view.accessibility.AccessibilityManager
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.LocalView
import care.visify.ui.kit.util.clickableNoInteraction

@Composable
fun VisifyHapticContainer(
    modifier: Modifier = Modifier,
    hapticFeedbackType: HapticFeedbackType = HapticFeedbackType.TextHandleMove,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    val localView = LocalView.current
    Box(
        modifier = modifier.clickableNoInteraction {
            when (hapticFeedbackType) {
                HapticFeedbackType.TextHandleMove -> localView.vibrate()
                HapticFeedbackType.LongPress -> localView.vibrateStrong()
            }
            onClick()
        }
    ) { content() }
}


private fun View.vibrate() = reallyPerformHapticFeedback(
    HapticFeedbackConstants.VIRTUAL_KEY
)

private fun View.vibrateStrong() = reallyPerformHapticFeedback(
    HapticFeedbackConstants.LONG_PRESS
)

private fun View.reallyPerformHapticFeedback(
    feedbackConstant: Int
) {
    if (context.isTouchExplorationEnabled()) {
        // Don't mess with a blind person's vibrations
        return
    }

    isHapticFeedbackEnabled = true
    performHapticFeedback(
        feedbackConstant,
        HapticFeedbackConstants.FLAG_IGNORE_VIEW_SETTING
    )
}

private fun Context.isTouchExplorationEnabled(): Boolean {
    val accessibilityManager = getSystemService(
        Context.ACCESSIBILITY_SERVICE
    ) as AccessibilityManager
    return accessibilityManager.isTouchExplorationEnabled
}