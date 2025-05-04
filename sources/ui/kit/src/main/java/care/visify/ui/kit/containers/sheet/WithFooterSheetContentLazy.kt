package care.visify.ui.kit.containers.sheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import care.visify.ui.icons.IconsR
import care.visify.ui.kit.components.divider.VisifyDivider
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.buttonActive
import care.visify.ui.kit.theme.values.buttonPrimary
import care.visify.ui.kit.theme.values.subheaderPrimary
import care.visify.ui.kit.util.asBgColor
import care.visify.ui.kit.util.clickableNoInteraction

@Deprecated("Use VisifySelectBottomSheet instead")
@Composable
fun WithFooterSheetContentLazy(
    title: String,
    isSelected: Boolean,
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
    onBackwards: () -> Unit = { },
    hasBackwards: Boolean = false,
    content: LazyListScope.() -> Unit,
) {

    Box(
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .asBgColor()
        ) {

            item {
                Text(
                    text = title,
                    style = VisifyTheme.visifyTypography.subheaderPrimary,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))
            }

            content()

            item { Spacer(modifier = Modifier.height(80.dp)) }
        }


        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(VisifyTheme.colors.frame.white)
                .clickableNoInteraction { }
        ) {
            VisifyDivider(modifier = Modifier.fillMaxWidth())
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(56.dp)
            ) {

                if (hasBackwards) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .clickableNoInteraction(onBackwards)
                            .align(Alignment.CenterStart)
                    ) {
                        Image(
                            painter = painterResource(id = IconsR.ic_back_24),
                            contentDescription = null
                        )
                        Text(
                            text = "Назад",
                            style = VisifyTheme.visifyTypography.buttonPrimary,
                        )
                    }
                }

                Text(
                    text = "Выбрать",
                    style = VisifyTheme.visifyTypography.buttonActive.copy(
                        color = if (isSelected.not()) VisifyTheme.colors.label.tertiary else VisifyTheme.colors.label.active
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clickableNoInteraction {
                            if (isSelected) onClose()
                        }
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}