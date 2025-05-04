package care.visify.ui.kit.time

//region time column

// Количество видимых элементов в столбце
internal const val countOfVisibleItemsInTimePicker = 5

// Высота одного элемента
internal const val timeItemHeight = 35f
internal const val timeItemWidth = 35f

// Высота списка
internal const val timeListHeight = countOfVisibleItemsInTimePicker * timeItemHeight

//endregion

//region time picker

internal val hoursRange = 0..23
internal val minutesRange = 0..59

// Ширина между колонками
internal const val spacerWidth = timeItemWidth * 1.5
// Высота центральной полоски
internal const val centerSelectorHeight = timeItemHeight * 1.1

//endregion