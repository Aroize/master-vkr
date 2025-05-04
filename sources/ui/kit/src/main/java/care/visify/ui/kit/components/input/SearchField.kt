package care.visify.ui.kit.components.input

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.mainCellPrimary
import care.visify.ui.kit.util.clickableNoInteraction

@Composable
fun SearchField(
    query: TextFieldValue,
    onValueChanged: (TextFieldValue) -> Unit,
    onSearchActive: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester = remember { FocusRequester() },
    trailing: @Composable ((PaddingValues) -> Unit)? = null,
    placeholder: @Composable ((PaddingValues) -> Unit)? = null,
    stopper: @Composable ((PaddingValues) -> Unit)? = null
) {
    var hasFocus by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    var containerWidth by remember { mutableStateOf(0) }
    var cancelWidth by remember { mutableStateOf(0) }

    val fraction = when {
        hasFocus.not() -> 1f
        else ->  (containerWidth - cancelWidth).toFloat() / containerWidth
    }

    val animatedFraction by animateFloatAsState(
        targetValue = fraction,
        animationSpec = spring(),
        label = "AnimateContainerWidth"
    )

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = modifier
                .fillMaxWidth(animatedFraction)
                .onSizeChanged {
                    if (hasFocus.not()) {
                        containerWidth = it.width
                    }
                }
        ) {
            if (query.text.isEmpty() && placeholder != null) {
                placeholder(PaddingValues(start = 16.dp))
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                BasicTextField(
                    value = query,
                    onValueChange = onValueChanged,
                    singleLine = true,
                    textStyle =  VisifyTheme.visifyTypography.mainCellPrimary,
                    cursorBrush = SolidColor(VisifyTheme.colors.label.active),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp)
                        .onFocusChanged {
                            hasFocus = it.hasFocus
                            onSearchActive(it.hasFocus)
                        }
                        .focusRequester(focusRequester)
                )
                if (query.text.isNotEmpty() && trailing != null) {
                    trailing(PaddingValues(end = 16.dp))
                }
            }
        }

        if (stopper != null) {
            AnimatedVisibility(
                modifier = Modifier.align(Alignment.CenterEnd),
                visible = hasFocus,
                enter = slideInHorizontally(
                    initialOffsetX = { 300 },
                    animationSpec = spring()
                ),
                exit = slideOutHorizontally(
                    targetOffsetX = { 300 },
                    animationSpec = spring()
                )
            ) {
                Box(
                    modifier = Modifier
                        .onSizeChanged { cancelWidth = it.width }
                        .clickableNoInteraction {
                            focusManager.clearFocus()
                        }
                ) {
                    stopper(PaddingValues(start = 12.dp, end = 16.dp))
                }
            }
        }
    }
}