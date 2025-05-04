package care.visify.ui.kit.theme.values

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

data class Colors(
    val frame : Frame,
    val label: Label,
    val dividers: Dividers,
) {

    data class Frame(
        val grey: Color = Color(0xFFF5F6FA),
        val white: Color = Color(0xFFFFFFFF),
        val whiteHalf: Color = Color(0x80FFFFFF),
        val active: Color = Color(0xFFFF335E),
        val activeHalf: Color = Color(0x80FF335E),
        val disabled: Color = Color(0xFFE6E6E6),
        val dialogOverlay: Color = Color(0x80404040),
        val yellow: Color = Color(0xFFFFCA0D),
        val navBgBlur: Color = Color(0xFFFFFFF0),
        val black: Color = Color(0xFF000000),
        val black2 : Color = Color(0xFF323234),
        val searchGrey: Color = Color(0xFF8D949E),
        val infoYellow : Color = Color(0xFFFFF3C8),
        val green : Color = Color(0xFF1D9169)
    )

    data class Label(
        val primary: Color = Color(0xFF404040),
        val secondary: Color = Color(0xFF808080),
        val tertiary: Color = Color(0xFFA6A6A6),
        val quaternary: Color = Color(0xFFD9D9D9),
        val white: Color = Color(0xFFFFFFFF),
        val active: Color = Color(0xFFFF335E),
        val error: Color = Color(0xFFDB3223),
    )

    data class Dividers(
        val main: Color = Color(0xFFDBDBDB),
        val second: Color = Color(0x80DBDBDB),
        val active: Color = Color(0xFFFF335E)
    )
}

data class VisifyTypography(
    val button: TextStyle,
    val navHeader: TextStyle,
    val headerLarge: TextStyle,
    val mainCell: TextStyle,
    val mainText: TextStyle,
    val mini: TextStyle,
    val micro: TextStyle,
    val subHeader: TextStyle
)

data class Padding(
    val _4dp: Dp,
    val _8dp: Dp,
    val _12dp: Dp,
    val _16dp: Dp,
    val _24dp: Dp,
    val _32dp: Dp,
)

data class CornerShape(
    val rounded6dp: CornerBasedShape,
    val roundedTop6dp: CornerBasedShape,
    val roundedBottom6dp: CornerBasedShape,
    val circle: CornerBasedShape,
)