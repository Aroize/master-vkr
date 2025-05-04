package care.visify.ui.kit.containers.cellitem

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility
import care.visify.ui.kit.R
import care.visify.ui.kit.components.divider.VisifyDivider
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.mainCellPrimary
import care.visify.ui.kit.util.cellShape
import care.visify.ui.kit.util.clickableNoInteraction

@Deprecated("Use `SelectableTextListItem`")
@Composable
fun SelectableListItem(
    text: String,
    textStyle: TextStyle,
    isSelected: Boolean,
    hasBottomDivider: Boolean,
    cellShape: Shape,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: Modifier = Modifier.paint(painter = painterResource(id = R.drawable.ic_done_24)),
    unselectedIcon: Modifier = Modifier,
) {

    Box(
        modifier = modifier
            .clickableNoInteraction { onClick() }
    ) {
        Text(
            text = text,
            style = textStyle,
            modifier = Modifier
                .fillMaxWidth()
                .background(VisifyTheme.colors.frame.white, cellShape)
                .padding(horizontal = 16.dp, vertical = 20.dp),
        )
        val bg = if (isSelected) selectedIcon else unselectedIcon
        Box(
            modifier = Modifier
                .padding(end = 16.dp)
                .then(bg)
                .size(24.dp)
                .align(Alignment.CenterEnd)
        )
        if (hasBottomDivider) {
            HorizontalDivider(
                thickness = 0.5.dp,
                color = VisifyTheme.colors.dividers.main,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
fun SelectableListItem(
    text: AnnotatedString,
    textStyle: TextStyle,
    isSelected: Boolean,
    hasBottomDivider: Boolean,
    cellShape: Shape,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clickableNoInteraction {
                onClick()
            }
    ) {
        Text(
            text = text,
            style = textStyle,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    VisifyTheme.colors.frame.white,
                    cellShape
                )
                .padding(horizontal = 16.dp, vertical = 20.dp),
        )
        val bg = if (isSelected) {
            Modifier.paint(painter = painterResource(id = R.drawable.ic_done_24))
        } else {
            Modifier
        }
        Box(
            modifier = Modifier
                .padding(end = 16.dp)
                .then(bg)
                .size(24.dp)
                .align(Alignment.CenterEnd)
        )
        if (hasBottomDivider) {
            HorizontalDivider(
                thickness = 0.5.dp,
                color = VisifyTheme.colors.dividers.main,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
fun SelectableTextListItem(
    text: String,
    textStyle: TextStyle,
    isSelected: Boolean,
    hasBottomDivider: Boolean,
    cellShape: Shape,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: Modifier = Modifier.paint(painter = painterResource(id = R.drawable.ic_done_24)),
    unselectedIcon: Modifier?,
) {
    SelectableListItemBase(
        content = {
            Text(
                text = text,
                style = textStyle,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp, vertical = 20.dp)
                    .fillMaxWidth()
            )
        },
        isSelected = isSelected,
        hasBottomDivider = hasBottomDivider,
        cellShape = cellShape,
        selectedIcon = selectedIcon,
        unselectedIcon = unselectedIcon,
        onClick = onClick,
        modifier = modifier
    )
}

@Composable
fun SelectableListItemBase(
    isSelected: Boolean,
    hasBottomDivider: Boolean,
    cellShape: Shape,
    modifier: Modifier = Modifier,
    selectedIcon: Modifier,
    unselectedIcon: Modifier?,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit,
) {
    Box {
        Row(
            modifier = modifier
                .background(VisifyTheme.colors.frame.white, cellShape)
                .clickableNoInteraction { onClick() },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            content()
            val bg = if (isSelected) selectedIcon else unselectedIcon
            Box(
                modifier = Modifier
                    .then(
                        when {
                            isSelected || unselectedIcon != null -> Modifier
                                .then(bg as Modifier)
                                .padding(horizontal = 16.dp)
                                .size(24.dp)

                            else -> Modifier
                        }
                    )
            )
        }
        if (hasBottomDivider) {
            HorizontalDivider(
                thickness = 0.5.dp,
                color = VisifyTheme.colors.dividers.main,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}


@SuppressLint("ModifierFactoryExtensionFunction")
sealed interface SelectedState {

    @Composable
    fun paint(): Modifier

    data class Selected(@DrawableRes val icon: Int) : SelectedState {
        @Composable
        override fun paint() = Modifier.paint(painterResource(id = icon))
    }

    data class Unselected(@DrawableRes val icon: Int) : SelectedState {
        @Composable
        override fun paint() = Modifier.paint(painterResource(id = icon))
    }

    data object Gone : SelectedState {
        @Composable
        override fun paint(): Modifier = Modifier
    }
}

fun Boolean.toSelectedState(
    @DrawableRes selectedIcon: Int,
    @DrawableRes unselectedIcon: Int,
) = when (this) {
    true -> SelectedState.Selected(selectedIcon)
    else -> SelectedState.Unselected(unselectedIcon)
}

fun Boolean.toSelectedStateWithGone(
    @DrawableRes selectedIcon: Int = R.drawable.ic_done_24,
) = when (this) {
    true -> SelectedState.Selected(selectedIcon)
    else -> SelectedState.Gone
}


@Composable
fun VisifyTextCellItem(
    text: String,
    shape: Shape,
    hasDivider: Boolean,
    selectedState: SelectedState,
    modifier: Modifier = Modifier,
    onIconClicked: () -> Unit = { },
    style: TextStyle = VisifyTheme.visifyTypography.mainCellPrimary,
) {
    VisifyCellItem(
        shape = shape,
        hasDivider = hasDivider,
        selectedState = selectedState,
        modifier = modifier,
        onIconClicked = onIconClicked,
        content = { iconRef ->
            val textRef = createRef()
            Text(
                text = text,
                style = style,
                textAlign = TextAlign.Start,
                modifier = Modifier.constrainAs(textRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(iconRef.end, margin = 16.dp)
                    width = Dimension.fillToConstraints
                },
            )
        }
    )
}

@Composable
fun VisifyTextCellItem(
    text: AnnotatedString,
    shape: Shape,
    hasDivider: Boolean,
    selectedState: SelectedState,
    modifier: Modifier = Modifier,
    onIconClicked: () -> Unit = { },
) {
    VisifyCellItem(
        shape = shape,
        hasDivider = hasDivider,
        selectedState = selectedState,
        modifier = modifier,
        onIconClicked = onIconClicked,
        content = { iconRef ->
            val textRef = createRef()
            Text(
                text = text,
                style = VisifyTheme.visifyTypography.mainCellPrimary,
                textAlign = TextAlign.Start,
                modifier = Modifier.constrainAs(textRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(iconRef.end, margin = 16.dp)
                    width = Dimension.fillToConstraints
                },
            )
        }
    )
}

@Composable
fun VisifyCellItem(
    shape: Shape? = null,
    hasDivider: Boolean = false,
    selectedState: SelectedState,
    modifier: Modifier = Modifier,
    onIconClicked: () -> Unit = { },
    innerPadding: PaddingValues = PaddingValues(horizontal = 16.dp),
    dividerPadding: PaddingValues = PaddingValues(),
    content: @Composable ConstraintLayoutScope.(ConstrainedLayoutReference) -> Unit,
) {
    ConstraintLayout(
        modifier = modifier
            .heightIn(min = 56.dp)
            .then(
                if (shape != null)
                    Modifier.background(VisifyTheme.colors.frame.white, shape)
                else
                    Modifier
            )
            .padding(innerPadding)
    ) {
        val (icon, divider) = createRefs()

        content(icon)

        Box(
            modifier = Modifier
                .height(24.dp)
                .then(selectedState.paint())
                .clickableNoInteraction(onIconClicked)
                .constrainAs(icon) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    visibility = when (selectedState) {
                        is SelectedState.Selected,
                        is SelectedState.Unselected,
                        -> Visibility.Visible

                        SelectedState.Gone -> Visibility.Gone
                    }
                }
        )

        VisifyDivider(
            modifier = Modifier
                .padding(dividerPadding)
                .constrainAs(divider) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    visibility = if (hasDivider) Visibility.Visible else Visibility.Invisible
                }
        )
    }
}

fun <T> LazyListScope.cellTextItems(
    items: List<T>,
    text: (T) -> String,
    selected: (T) -> SelectedState,
    onClick: (T) -> Unit,
    modifier: Modifier = Modifier,
) {
    itemsIndexed(items) { idx, item ->
        val cellShape = cellShape(items, idx, size = 6.dp)
        VisifyTextCellItem(
            text = text(item),
            shape = cellShape,
            hasDivider = idx != items.lastIndex,
            selectedState = selected(item),
            modifier = modifier
                .fillMaxWidth()
                .clickableNoInteraction { onClick(item) }
        )
    }
}