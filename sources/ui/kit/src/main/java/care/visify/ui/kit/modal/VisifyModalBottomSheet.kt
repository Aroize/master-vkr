package care.visify.ui.kit.modal

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.modal.footers.VisifyEmptyFooter
import care.visify.ui.kit.modal.handle.VisifyDragHandle
import care.visify.ui.kit.theme.VisifyTheme
import kotlinx.coroutines.flow.filter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> VisifyModalBottomSheet(
    visifySheetState: VisifySheetState<T>,
    contentModifier: Modifier = Modifier,
    dragHandle: (@Composable () -> Unit) = { VisifyDragHandle() },
    header: @Composable () -> Unit = { },
    footer: @Composable () -> Unit = { VisifyEmptyFooter() },
    onDismiss: () -> Unit = { },
    content: @Composable ColumnScope.(T) -> Unit,
) {

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    //если вот так сделать, то щит не будет закрываться по свайпу, но если закрыть по backPress, то все инпуты зависают и не обрабатываются
//    val sheetState =
//        rememberModalBottomSheetState(skipPartiallyExpanded = true, confirmValueChange = { newState ->
//            newState != SheetValue.Hidden
//        })

    LaunchedEffect(Unit) {
        snapshotFlow { visifySheetState.isVisible }
            .filter { visible -> visible.not() }
            .collect { _ ->
                sheetState.hide()
            }
    }

    if (visifySheetState.isVisible || sheetState.isVisible) {
        ModalBottomSheet(
            onDismissRequest = {
                visifySheetState.hide()
                onDismiss()
            },
            sheetState = sheetState,
            shape = RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp),
            containerColor = VisifyTheme.colors.frame.grey,
            dragHandle = dragHandle,
            content = {
                val density = LocalDensity.current
                val configuration = LocalConfiguration.current
                var actualFooterPadding by remember { mutableStateOf(0.dp) }
                var actualHeaderPadding by remember { mutableStateOf(0.dp) }
                val maxContentHeight =
                    configuration.screenHeightDp.dp * VisifySheetState.MAX_FRACTION

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = maxContentHeight)
                ) {
                    Box(
                        modifier = Modifier
                            .padding(
                                top = actualHeaderPadding,
                                bottom = actualFooterPadding
                            )
                            .align(Alignment.TopCenter)
                    ) {
                        Column(
                            modifier = contentModifier
                                .verticalScroll(rememberScrollState()),
                            content = { content(visifySheetState.unsafeData) }
                        )
                    }

                    Box(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .onSizeChanged { size ->
                                actualHeaderPadding = with(density) { size.height.toDp() }
                            },
                        content = { header() }
                    )

                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .onSizeChanged { size ->
                                actualFooterPadding = with(density) { size.height.toDp() }
                            },
                        content = { footer() }
                    )
                }
            }
        )
    }
}