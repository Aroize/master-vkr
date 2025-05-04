package care.visify.ui.kit.containers.sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import care.visify.ui.kit.theme.VisifyTheme

@Composable
internal fun WithFooterContent(
    paddingValues: PaddingValues,
    content: @Composable ColumnScope.() -> Unit,
    footer: @Composable BoxScope.() -> Unit
) {
    val scroll = rememberScrollState()
    Box(
        modifier = Modifier.wrapContentHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scroll)
                .padding(paddingValues),
            content = { content() }
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(paddingValues.calculateBottomPadding())
                .align(Alignment.BottomCenter)
                .background(VisifyTheme.colors.frame.white),
            content = { footer() }
        )
    }
}