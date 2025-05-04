package care.visify.ui.kit.modal.footers

import androidx.compose.runtime.Composable
import care.visify.ui.kit.theme.VisifyTheme

@Composable
fun SelectModalFooter(
    isEnable: Boolean,
    onSelectClick: () -> Unit,
) {
    VisifyModalFooter(
        style = VisifyFooterContent.Right(
            content = {
                VisifyFooterButton(button = VisifyFooterBtn.TextButton(
                    text = "Выбрать",
                    color = if (isEnable.not()) {
                        VisifyTheme.colors.label.tertiary
                    } else {
                        VisifyTheme.colors.label.active
                    },
                    isVisible = true
                ) {
                    if (isEnable) {
                        onSelectClick()
                    }
                })
            },
        )
    )
}

@Composable
fun SelectBackwardModalFooter(
    isSelectEnable: Boolean,
    onSelectClick: () -> Unit,
    onBackwardClick: () -> Unit,
) {
    VisifyModalFooter(
        style = VisifyFooterContent.Both(
            right = {
                VisifyFooterButton(
                    button = VisifyFooterBtn.TextButton(
                        text = "Выбрать",
                        color = if (isSelectEnable.not()) {
                            VisifyTheme.colors.label.tertiary
                        } else {
                            VisifyTheme.colors.label.active
                        },
                        isVisible = true,
                        onClick = {
                            if (isSelectEnable) {
                                onSelectClick()
                            }
                        }
                    )
                )
            },
            left = {
                VisifyFooterButton(
                    button = VisifyFooterBtn.TextButton(
                        text = "Назад",
                        color = VisifyTheme.colors.label.primary,
                        isVisible = true,
                        onClick = onBackwardClick
                    )
                )
            }
        )
    )
}