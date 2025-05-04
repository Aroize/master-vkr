package care.visify.ui.kit.time

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.util.getTimeDefaultStr
import care.visify.ui.kit.util.pixelsToDp
import kotlinx.collections.immutable.toPersistentList
import kotlin.math.abs

internal fun LazyListState.itemForScrollTo(context: Context): Int {
    val offset = firstVisibleItemScrollOffset.pixelsToDp(context)
    return when {
        offset == 0f -> firstVisibleItemIndex
        offset % timeItemHeight >= timeItemHeight / 2 -> firstVisibleItemIndex + 1
        else -> firstVisibleItemIndex
    }
}

@Composable
fun TimeColumnPicker(
    modifier: Modifier = Modifier,
    selectedValue: Int,
    onValueChange: (Int) -> Unit,
    range: IntRange,
) {
    val context = LocalContext.current
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = selectedValue)

    LaunchedEffect(listState.isScrollInProgress) {
        if (!listState.isScrollInProgress && listState.firstVisibleItemScrollOffset.pixelsToDp(
                context
            ) % timeItemHeight != 0f // иначе будет постоянная рекомпозиция
        ) {
            // Перемотка к центральному элементу
            listState.animateScrollToItem(listState.itemForScrollTo(context = context))
        }
    }

    // Генерация списка значений времени.
    val list by remember {
        mutableStateOf(
            mutableListOf<String>().apply {
                (1..(countOfVisibleItemsInTimePicker / 2)).forEach { _ -> add("") }
                for (i in range) add(i.getTimeDefaultStr())
                (1..(countOfVisibleItemsInTimePicker / 2)).forEach { _ -> add("") }
            }.toPersistentList()
        )
    }

    Box(
        modifier = modifier.height(timeListHeight.dp).width(timeItemWidth.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(state = listState) {
            itemsIndexed(items = list) { index, it ->
                Box(
                    modifier = Modifier.fillParentMaxHeight(1f / countOfVisibleItemsInTimePicker),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = it,
                        modifier = Modifier.graphicsLayer(
                            scaleX = calculateScaleX(listState, index),
                            scaleY = calculateScaleY(listState, index),
                            alpha = calculateAlpha(index, listState)
                        ),
                        style = VisifyTheme.visifyTypography.subHeader,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }

    val offset by remember { derivedStateOf { listState.firstVisibleItemScrollOffset } }
    LaunchedEffect(offset) {
        val newValue =
            list[listState.itemForScrollTo(context) + countOfVisibleItemsInTimePicker / 2].toIntOrNull()
        if (newValue != null && newValue != selectedValue) {
            onValueChange(newValue)
        }
    }
}

internal fun calculateScaleX(listState: LazyListState, index: Int): Float {
    // Получаем информацию о текущем состоянии компоновки списка
    val layoutInfo = listState.layoutInfo
    // Извлекаем индексы видимых элементов
    val visibleItems = layoutInfo.visibleItemsInfo.map { it.index }
    // Если элемент не виден, возвращаем масштаб 1 (нормальный)
    if (!visibleItems.contains(index)) return 1f
    // Находим информацию о конкретном элементе по индексу
    val itemInfo = layoutInfo.visibleItemsInfo.firstOrNull { it.index == index } ?: return 1f
    // Вычисляем центр видимой области
    val center = (layoutInfo.viewportEndOffset + layoutInfo.viewportStartOffset) / 2f
    // Вычисляем расстояние от центра до середины элемента
    val distance = abs((itemInfo.offset + itemInfo.size / 2) - center)
    // Максимальное расстояние до центра для расчета масштаба
    val maxDistance = layoutInfo.viewportEndOffset / 2f
    // Сжимаем элемент до половины при максимальном расстоянии
    return 1f - (distance / maxDistance) * 0.5f
}

internal fun calculateScaleY(listState: LazyListState, index: Int): Float {
    // Получаем информацию о текущем состоянии компоновки списка
    val layoutInfo = listState.layoutInfo
    // Извлекаем индексы видимых элементов
    val visibleItems = layoutInfo.visibleItemsInfo.map { it.index }
    // Если элемент не виден, возвращаем масштаб 1 (нормальный)
    if (!visibleItems.contains(index)) return 1f
    // Находим информацию о конкретном элементе по индексу
    val itemInfo = layoutInfo.visibleItemsInfo.firstOrNull { it.index == index } ?: return 1f
    // Вычисляем центр видимой области
    val center = (layoutInfo.viewportEndOffset + layoutInfo.viewportStartOffset) / 2f
    // Вычисляем расстояние от центра до середины элемента
    val distance = abs((itemInfo.offset + itemInfo.size / 2) - center)
    // Максимальное расстояние до центра для расчета масштаба
    val maxDistanceY = layoutInfo.viewportEndOffset / 2f
    // Сжимаем элемент полностью при максимальном расстоянии
    return 1f - (distance / maxDistanceY)
}

internal fun calculateAlpha(index: Int, listState: LazyListState): Float {
    // Получаем информацию о текущем состоянии компоновки списка
    val layoutInfo = listState.layoutInfo
    // Извлекаем индексы видимых элементов
    val visibleItems = layoutInfo.visibleItemsInfo.map { it.index }
    // Если нет видимых элементов, возвращаем максимальную непрозрачность
    if (visibleItems.isEmpty()) return 1f
    // Вычисляем центр видимой области
    val center = (layoutInfo.viewportEndOffset + layoutInfo.viewportStartOffset) / 2f
    // Находим информацию о конкретном элементе по индексу
    val itemInfo = layoutInfo.visibleItemsInfo.firstOrNull { it.index == index } ?: return 1f
    // Вычисляем расстояние от центра до середины элемента
    val distance = abs((itemInfo.offset + itemInfo.size / 2) - center)
    // Максимальное расстояние для расчета прозрачности
    val maxDistance = layoutInfo.viewportEndOffset / 2f
    // Уменьшаем прозрачность до 0.3 при максимальном расстоянии
    return 1f - (distance / maxDistance) * 0.7f
}