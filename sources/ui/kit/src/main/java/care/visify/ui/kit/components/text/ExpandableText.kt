package care.visify.ui.kit.components.text

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import care.visify.ui.kit.theme.VisifyTheme

@Stable
data class ExpandableTextState(
    val text: String,
    val limit: Int,
    val isExpanded: Boolean
)

@Composable
fun ExpandableText(
    state: ExpandableTextState,
    style: TextStyle,
    modifier: Modifier = Modifier,
    onExpand: () -> Unit,
) {
    val isExpanded = state.isExpanded

    val annotatedText = buildAnnotatedString {
        val subText = if (state.isExpanded) state.text else state.text.take(state.limit)
        append(subText)

        // If the text isn't expanded, add an ellipsis and a clickable annotation
        if (!isExpanded && state.text.length > state.limit) {
            append("\n\nЕще...")
            addStyle(
                style = SpanStyle(
                    textDecoration = TextDecoration.None,
                    color = VisifyTheme.colors.label.active
                ),
                start = subText.length,
                end = length
            )
            addStringAnnotation(
                tag = "click",
                annotation = "see_more",
                start = subText.length,
                end = length
            )
        }
    }

    ClickableText(
        text = annotatedText,
        style = style,
        onClick = { offset ->
            annotatedText.getStringAnnotations("click", offset, offset).firstOrNull()?.let {
                onExpand()
            }
        },
        modifier = modifier
    )
}