package care.visify.ui.kit.modal.footers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.components.divider.VisifyDivider
import care.visify.ui.kit.theme.VisifyTheme


sealed interface VisifyFooterContent {

    sealed class OneSide(
        val content: @Composable () -> Unit
    ): VisifyFooterContent


    class Left(content: @Composable () -> Unit): OneSide(content)
    class Right(content: @Composable () -> Unit): OneSide(content)
    class Center(content: @Composable () -> Unit): OneSide(content)

    data class Both(
        val right: @Composable () -> Unit,
        val left: @Composable () -> Unit,
    ): VisifyFooterContent
}

@Composable
fun VisifyModalFooter(
    style: VisifyFooterContent
) {
    Box(
        modifier = Modifier
            .background(VisifyTheme.colors.frame.white)
            .navigationBarsPadding()
            .height(56.dp)
    ) {

        when (style) {
            is VisifyFooterContent.Both -> {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = VisifyTheme.padding._16dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    style.left()
                    style.right()
                }
            }
            is VisifyFooterContent.OneSide -> {
                val alignment = when (style) {
                    is VisifyFooterContent.Left -> Alignment.CenterStart
                    is VisifyFooterContent.Right -> Alignment.CenterEnd
                    is VisifyFooterContent.Center -> Alignment.Center
                }

                Box(
                    modifier = Modifier
                        .align(alignment)
                        .padding(horizontal = VisifyTheme.padding._16dp)
                ) {
                    style.content()
                }
            }
        }

        VisifyDivider(
            Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter))
    }
}