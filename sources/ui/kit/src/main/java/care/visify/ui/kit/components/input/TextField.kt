package care.visify.ui.kit.components.input

import android.content.ClipboardManager
import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility
import care.visify.ui.kit.components.divider.VisifyDivider
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.mainCellPrimary
import care.visify.ui.kit.theme.values.mainCellTertiary
import care.visify.ui.kit.theme.values.microTertiary
import care.visify.ui.kit.theme.values.miniActive
import care.visify.ui.kit.util.animateSpAsState
import care.visify.ui.kit.util.clickableNoInteraction

@Composable
fun VisifyInputField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier,
    showHint: Boolean = true,
    lineHint: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    showUnderlayDivider: Boolean = enabled,
    innerPadding: PaddingValues = PaddingValues(horizontal = 16.dp)
) {


    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    val actualKeyboardActions = KeyboardActions(
        onDone = keyboardActions.onDone ?: { focusManager.clearFocus() },
        onGo = keyboardActions.onGo,
        onNext = keyboardActions.onNext,
        onPrevious = keyboardActions.onPrevious,
        onSearch = keyboardActions.onSearch,
        onSend = keyboardActions.onSend
    )

    ConstraintLayout(
        modifier = modifier
            .heightIn(min = 64.dp)
            .padding(innerPadding)
    ) {

        var hasFocus by remember { mutableStateOf(false) }
        val (inputRef, smallHint, bigHint, divider, lineHintRef, spacerRef) = createRefs()

        val animatedSp by animateSpAsState(
            targetValue = if (value.isNotEmpty()) VisifyTheme.visifyTypography.micro.fontSize else 0.sp
        )
        Text(
            text = hint,
            style = VisifyTheme.visifyTypography.microTertiary,
            fontSize = animatedSp,
            modifier = Modifier.constrainAs(smallHint) {
                start.linkTo(parent.start)
                bottom.linkTo(inputRef.top, margin = 3.dp)
                visibility = when {
                    value.isEmpty() -> Visibility.Gone
                    showHint -> Visibility.Visible
                    else -> Visibility.Gone
                }
            }
        )

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            cursorBrush = SolidColor(VisifyTheme.colors.label.active),
            modifier = Modifier
                .focusRequester(focusRequester)
                .onFocusChanged { hasFocus = it.hasFocus }
                .heightIn(min = 18.dp)
                .constrainAs(inputRef) {
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    top.linkTo(parent.top, margin = 31.dp)
                    width = Dimension.fillToConstraints
                },
            textStyle = when (enabled) {
                true -> VisifyTheme.visifyTypography.mainCellPrimary
                else -> VisifyTheme.visifyTypography.mainCellTertiary
            },
            keyboardActions = actualKeyboardActions,
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            enabled = enabled,
            maxLines = maxLines
        )

        Text(
            text = hint,
            style = VisifyTheme.visifyTypography.mainCellTertiary,
            modifier = Modifier.constrainAs(bigHint) {
                start.linkTo(inputRef.start)
                bottom.linkTo(inputRef.bottom)
                visibility = when {
                    value.isNotEmpty() -> Visibility.Invisible
                    showHint -> Visibility.Visible
                    else -> Visibility.Invisible
                }
            }
        )

        VisifyDivider(
            modifier = Modifier
                .constrainAs(divider) {
                    end.linkTo(inputRef.end)
                    start.linkTo(inputRef.start)
                    top.linkTo(inputRef.bottom, margin = 6.dp)
                    visibility = when (showUnderlayDivider) {
                        true -> Visibility.Visible
                        else -> Visibility.Gone
                    }
                },
            thickness = when (hasFocus) {
                true -> 2.dp
                else -> 0.5.dp
            },
            color = when (hasFocus) {
                true -> VisifyTheme.colors.label.active
                else -> VisifyTheme.colors.dividers.main
            }
        )

        val lineHintTopMargin = when {
            hasFocus -> 10.dp
            else -> 11.5.dp
        }

        Text(
            text = lineHint.orEmpty(),
            style = VisifyTheme.visifyTypography.miniActive,
            modifier = Modifier.constrainAs(lineHintRef) {
                start.linkTo(divider.start)
                end.linkTo(divider.end)
                top.linkTo(divider.bottom, margin = lineHintTopMargin)
                visibility = when (lineHint) {
                    null -> Visibility.Gone
                    else -> Visibility.Visible
                }
            }
        )

        val space = when {
            lineHint != null -> 5.dp
            hasFocus -> 7.dp
            else -> 8.5.dp
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(space)
                .constrainAs(spacerRef) {
                    top.linkTo(lineHintRef.bottom)
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}

@Composable
fun VisifyInputField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    hint: String,
    modifier: Modifier = Modifier,
    showHint: Boolean = true,
    lineHint: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    insertShown: Boolean = false,
    showUnderlayDivider: Boolean = true,
    innerPadding: PaddingValues = PaddingValues(horizontal = 16.dp)
) {

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    val actualKeyboardActions = KeyboardActions(
        onDone = keyboardActions.onDone ?: { focusManager.clearFocus() },
        onGo = keyboardActions.onGo,
        onNext = keyboardActions.onNext,
        onPrevious = keyboardActions.onPrevious,
        onSearch = keyboardActions.onSearch,
        onSend = keyboardActions.onSend
    )

    ConstraintLayout(
        modifier = modifier
            .heightIn(min = 64.dp)
            .padding(innerPadding)
    ) {

        var hasFocus by remember { mutableStateOf(false) }
        val (inputRef, smallHint, bigHint, divider, lineHintRef, spacerRef, insertRef) = createRefs()

        val animatedSp by animateSpAsState(
            targetValue = if (value.text.isNotEmpty()) VisifyTheme.visifyTypography.micro.fontSize else 0.sp
        )
        Text(
            text = hint,
            style = VisifyTheme.visifyTypography.microTertiary,
            fontSize = animatedSp,
            modifier = Modifier.constrainAs(smallHint) {
                start.linkTo(parent.start)
                bottom.linkTo(inputRef.top, margin = 3.dp)
                visibility = when {
                    value.text.isEmpty() -> Visibility.Gone
                    showHint -> Visibility.Visible
                    else -> Visibility.Gone
                }
            }
        )

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            cursorBrush = SolidColor(VisifyTheme.colors.label.active),
            modifier = Modifier
                .focusRequester(focusRequester)
                .onFocusChanged { hasFocus = it.hasFocus }
                .heightIn(min = 18.dp)
                .constrainAs(inputRef) {
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    top.linkTo(parent.top, margin = 31.dp)
                    width = Dimension.fillToConstraints
                },
            textStyle = when (enabled) {
                true -> VisifyTheme.visifyTypography.mainCellPrimary
                else -> VisifyTheme.visifyTypography.mainCellTertiary
            },
            keyboardActions = actualKeyboardActions,
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            enabled = enabled,
            maxLines = maxLines
        )

        val ctx = LocalContext.current
        val clipboardManager = ctx.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        Text(
            text = "Вставить",
            style = VisifyTheme.visifyTypography.mainCellPrimary,
            modifier = Modifier
                .clickableNoInteraction {
                    clipboardManager.primaryClip
                        ?.takeIf { data -> data.itemCount > 0 }
                        ?.getItemAt(0)
                        ?.let {
                            onValueChange(
                                TextFieldValue(
                                    it.text.toString(),
                                    TextRange(it.text.length)
                                )
                            )
                        }
                }
                .constrainAs(insertRef) {
                    end.linkTo(inputRef.end)
                    bottom.linkTo(inputRef.bottom)
                    visibility = when (insertShown) {
                        true -> Visibility.Visible
                        else -> Visibility.Gone
                    }
                }
        )

        Text(
            text = hint,
            style = VisifyTheme.visifyTypography.mainCellTertiary,
            modifier = Modifier.constrainAs(bigHint) {
                start.linkTo(inputRef.start)
                bottom.linkTo(inputRef.bottom)
                visibility = when {
                    value.text.isNotEmpty() -> Visibility.Invisible
                    showHint -> Visibility.Visible
                    else -> Visibility.Invisible
                }
            }
        )

        VisifyDivider(
            modifier = Modifier
                .constrainAs(divider) {
                    end.linkTo(inputRef.end)
                    start.linkTo(inputRef.start)
                    top.linkTo(inputRef.bottom, margin = 3.dp)
                    visibility = when (showUnderlayDivider) {
                        true -> Visibility.Visible
                        else -> Visibility.Gone
                    }
                },
            thickness = when (hasFocus) {
                true -> 2.dp
                else -> 0.5.dp
            },
            color = when (hasFocus) {
                true -> VisifyTheme.colors.label.active
                else -> VisifyTheme.colors.dividers.main
            }
        )

        val lineHintTopMargin = when {
            hasFocus -> 10.dp
            else -> 11.5.dp
        }

        Text(
            text = lineHint.orEmpty(),
            style = VisifyTheme.visifyTypography.miniActive,
            modifier = Modifier.constrainAs(lineHintRef) {
                start.linkTo(divider.start)
                end.linkTo(divider.end)
                top.linkTo(divider.bottom, margin = lineHintTopMargin)
                visibility = when (lineHint) {
                    null -> Visibility.Gone
                    else -> Visibility.Visible
                }
            }
        )

        val space = when {
            lineHint != null -> 5.dp
            hasFocus -> 7.dp
            else -> 8.5.dp
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(space)
                .constrainAs(spacerRef) {
                    top.linkTo(lineHintRef.bottom)
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}

@Composable
fun WriteBarInputField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    maxLines: Int
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    val actualKeyboardActions = KeyboardActions(
        onDone = keyboardActions.onDone ?: { focusManager.clearFocus() },
        onGo = keyboardActions.onGo,
        onNext = keyboardActions.onNext,
        onPrevious = keyboardActions.onPrevious,
        onSearch = keyboardActions.onSearch,
        onSend = keyboardActions.onSend
    )

    ConstraintLayout(modifier = modifier) {

        var hasFocus by remember { mutableStateOf(false) }
        val (inputRef, bigHint) = createRefs()

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            cursorBrush = SolidColor(VisifyTheme.colors.label.active),
            modifier = Modifier
                .focusRequester(focusRequester)
                .onFocusChanged { hasFocus = it.hasFocus }
                .heightIn(min = 18.dp)
                .constrainAs(inputRef) {
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                },
            textStyle = VisifyTheme.visifyTypography.mainCellPrimary,
            keyboardActions = actualKeyboardActions,
            keyboardOptions = keyboardOptions,
            maxLines = maxLines
        )

        Text(
            text = hint,
            style = VisifyTheme.visifyTypography.mainCellTertiary,
            modifier = Modifier.constrainAs(bigHint) {
                start.linkTo(inputRef.start)
                bottom.linkTo(inputRef.bottom)
                visibility = when {
                    value.isNotEmpty() -> Visibility.Invisible
                    else -> Visibility.Visible
                }
            }
        )
    }
}