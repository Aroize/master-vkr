package care.visify.ui.kit.modal.footers

import androidx.compose.runtime.Composable
import care.visify.core.util.multiLet
import care.visify.ui.kit.theme.VisifyTheme


@Composable
fun GeneralModalFooter(
    rightText: String,
    isRightEnable: Boolean,
    onRightClick: () -> Unit,
    leftText: String? = null,
    onLeftClick: (() -> Unit)? = null,
) {
    VisifyModalFooter(
        style = VisifyFooterContent.Both(
            right = {
                VisifyFooterButton(
                    button = VisifyFooterBtn.TextButton(
                        text = rightText,
                        color = if (isRightEnable.not()) {
                            VisifyTheme.colors.label.tertiary
                        } else {
                            VisifyTheme.colors.label.active
                        },
                        isVisible = true,
                        onClick = {
                            if (isRightEnable) {
                                onRightClick()
                            }
                        }
                    )
                )
            },
            left = {
                multiLet(leftText, onLeftClick) { text, click ->
                    VisifyFooterButton(
                        button = VisifyFooterBtn.TextButton(
                            text = text,
                            color = VisifyTheme.colors.label.primary,
                            isVisible = true,
                            onClick = click
                        )
                    )
                }
            }
        )
    )
}