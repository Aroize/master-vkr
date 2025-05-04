package care.visify.ui.kit.theme.values

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import care.visify.ui.kit.R
import care.visify.ui.kit.theme.VisifyTheme

internal val gilroyBold = FontFamily(
    Font(R.font.gilroy_bold, FontWeight.Bold),
)

internal val gilroySemiBold = FontFamily(
    Font(R.font.gilroy_semibold, FontWeight.SemiBold)
)

internal val visifyTypography = VisifyTypography(
    button = TextStyle(
        fontFamily = gilroyBold,
        fontSize = 15.sp,
        lineHeight = 18.sp
    ),
    navHeader = TextStyle(
        fontFamily = gilroyBold,
        fontSize = 16.sp,
        lineHeight = 19.sp
    ),
    headerLarge = TextStyle(
        fontFamily = gilroyBold,
        fontSize = 26.sp,
        lineHeight = 30.sp
    ),
    mainCell = TextStyle(
        fontFamily = gilroySemiBold,
        fontSize = 15.sp,
        lineHeight = 18.sp
    ),
    mainText = TextStyle(
        fontFamily = gilroySemiBold,
        fontSize = 14.sp,
        lineHeight = 17.sp
    ),
    mini = TextStyle(
        fontFamily = gilroySemiBold,
        fontSize = 12.sp,
        lineHeight = 15.sp
    ),
    micro = TextStyle(
        fontFamily = gilroySemiBold,
        fontSize = 11.sp,
        lineHeight = 13.sp
    ),
    subHeader = TextStyle(
        fontFamily = gilroyBold,
        fontSize = 18.sp,
        lineHeight = 24.sp
    )
)

val VisifyTypography.buttonPrimary: TextStyle
    @Composable
    get() = button.copy(color = VisifyTheme.colors.label.primary)

val VisifyTypography.navHeaderPrimary: TextStyle
    @Composable
    get() = navHeader.copy(color = VisifyTheme.colors.label.primary)

val VisifyTypography.headerLargePrimary: TextStyle
    @Composable
    get() = headerLarge.copy(color = VisifyTheme.colors.label.primary)

val VisifyTypography.mainCellPrimary: TextStyle
    @Composable
    get() = mainCell.copy(color = VisifyTheme.colors.label.primary)

val VisifyTypography.mainTextPrimary: TextStyle
    @Composable
    get() = mainText.copy(color = VisifyTheme.colors.label.primary)

val VisifyTypography.miniPrimary: TextStyle
    @Composable
    get() = mini.copy(color = VisifyTheme.colors.label.primary)

val VisifyTypography.microPrimary: TextStyle
    @Composable
    get() = micro.copy(color = VisifyTheme.colors.label.primary)

val VisifyTypography.subheaderPrimary: TextStyle
    @Composable
    get() = subHeader.copy(color = VisifyTheme.colors.label.primary)

// If you need secondary colors
val VisifyTypography.buttonSecondary: TextStyle
    @Composable
    get() = button.copy(color = VisifyTheme.colors.label.secondary)

val VisifyTypography.navHeaderSecondary: TextStyle
    @Composable
    get() = navHeader.copy(color = VisifyTheme.colors.label.secondary)

val VisifyTypography.headerLargeSecondary: TextStyle
    @Composable
    get() = headerLarge.copy(color = VisifyTheme.colors.label.secondary)

val VisifyTypography.mainCellSecondary: TextStyle
    @Composable
    get() = mainCell.copy(color = VisifyTheme.colors.label.secondary)

val VisifyTypography.mainTextSecondary: TextStyle
    @Composable
    get() = mainText.copy(color = VisifyTheme.colors.label.secondary)

val VisifyTypography.miniSecondary: TextStyle
    @Composable
    get() = mini.copy(color = VisifyTheme.colors.label.secondary)

val VisifyTypography.microSecondary: TextStyle
    @Composable
    get() = micro.copy(color = VisifyTheme.colors.label.secondary)

val VisifyTypography.subheaderSecondary: TextStyle
    @Composable
    get() = subHeader.copy(color = VisifyTheme.colors.label.secondary)

// Tertiary colors
val VisifyTypography.buttonTertiary: TextStyle
    @Composable
    get() = button.copy(color = VisifyTheme.colors.label.tertiary)

val VisifyTypography.navHeaderTertiary: TextStyle
    @Composable
    get() = navHeader.copy(color = VisifyTheme.colors.label.tertiary)

val VisifyTypography.headerLargeTertiary: TextStyle
    @Composable
    get() = headerLarge.copy(color = VisifyTheme.colors.label.tertiary)

val VisifyTypography.mainCellTertiary: TextStyle
    @Composable
    get() = mainCell.copy(color = VisifyTheme.colors.label.tertiary)

val VisifyTypography.mainTextTertiary: TextStyle
    @Composable
    get() = mainText.copy(color = VisifyTheme.colors.label.tertiary)

val VisifyTypography.miniTertiary: TextStyle
    @Composable
    get() = mini.copy(color = VisifyTheme.colors.label.tertiary)

val VisifyTypography.microTertiary: TextStyle
    @Composable
    get() = micro.copy(color = VisifyTheme.colors.label.tertiary)

val VisifyTypography.subheaderTertiary: TextStyle
    @Composable
    get() = subHeader.copy(color = VisifyTheme.colors.label.tertiary)

// Quaternary colors
val VisifyTypography.buttonQuaternary: TextStyle
    @Composable
    get() = button.copy(color = VisifyTheme.colors.label.quaternary)

val VisifyTypography.navHeaderQuaternary: TextStyle
    @Composable
    get() = navHeader.copy(color = VisifyTheme.colors.label.quaternary)

val VisifyTypography.headerLargeQuaternary: TextStyle
    @Composable
    get() = headerLarge.copy(color = VisifyTheme.colors.label.quaternary)

val VisifyTypography.mainCellQuaternary: TextStyle
    @Composable
    get() = mainCell.copy(color = VisifyTheme.colors.label.quaternary)

val VisifyTypography.mainTextQuaternary: TextStyle
    @Composable
    get() = mainText.copy(color = VisifyTheme.colors.label.quaternary)

val VisifyTypography.miniQuaternary: TextStyle
    @Composable
    get() = mini.copy(color = VisifyTheme.colors.label.quaternary)

val VisifyTypography.microQuaternary: TextStyle
    @Composable
    get() = micro.copy(color = VisifyTheme.colors.label.quaternary)

val VisifyTypography.subheaderQuaternary: TextStyle
    @Composable
    get() = subHeader.copy(color = VisifyTheme.colors.label.quaternary)

// White colors
val VisifyTypography.buttonWhite: TextStyle
    @Composable
    get() = button.copy(color = VisifyTheme.colors.label.white)

val VisifyTypography.navHeaderWhite: TextStyle
    @Composable
    get() = navHeader.copy(color = VisifyTheme.colors.label.white)

val VisifyTypography.headerLargeWhite: TextStyle
    @Composable
    get() = headerLarge.copy(color = VisifyTheme.colors.label.white)

val VisifyTypography.mainCellWhite: TextStyle
    @Composable
    get() = mainCell.copy(color = VisifyTheme.colors.label.white)

val VisifyTypography.mainTextWhite: TextStyle
    @Composable
    get() = mainText.copy(color = VisifyTheme.colors.label.white)

val VisifyTypography.miniWhite: TextStyle
    @Composable
    get() = mini.copy(color = VisifyTheme.colors.label.white)

val VisifyTypography.microWhite: TextStyle
    @Composable
    get() = micro.copy(color = VisifyTheme.colors.label.white)

val VisifyTypography.subheaderWhite: TextStyle
    @Composable
    get() = subHeader.copy(color = VisifyTheme.colors.label.white)

// Active colors
val VisifyTypography.buttonActive: TextStyle
    @Composable
    get() = button.copy(color = VisifyTheme.colors.label.active)

val VisifyTypography.navHeaderActive: TextStyle
    @Composable
    get() = navHeader.copy(color = VisifyTheme.colors.label.active)

val VisifyTypography.headerLargeActive: TextStyle
    @Composable
    get() = headerLarge.copy(color = VisifyTheme.colors.label.active)

val VisifyTypography.mainCellActive: TextStyle
    @Composable
    get() = mainCell.copy(color = VisifyTheme.colors.label.active)

val VisifyTypography.mainTextActive: TextStyle
    @Composable
    get() = mainText.copy(color = VisifyTheme.colors.label.active)

val VisifyTypography.miniActive: TextStyle
    @Composable
    get() = mini.copy(color = VisifyTheme.colors.label.active)

val VisifyTypography.microActive: TextStyle
    @Composable
    get() = micro.copy(color = VisifyTheme.colors.label.active)

val VisifyTypography.subheaderActive: TextStyle
    @Composable
    get() = subHeader.copy(color = VisifyTheme.colors.label.active)

// Error colors
val VisifyTypography.buttonError: TextStyle
    @Composable
    get() = button.copy(color = VisifyTheme.colors.label.error)

val VisifyTypography.navHeaderError: TextStyle
    @Composable
    get() = navHeader.copy(color = VisifyTheme.colors.label.error)

val VisifyTypography.headerLargeError: TextStyle
    @Composable
    get() = headerLarge.copy(color = VisifyTheme.colors.label.error)

val VisifyTypography.mainCellError: TextStyle
    @Composable
    get() = mainCell.copy(color = VisifyTheme.colors.label.error)

val VisifyTypography.mainTextError: TextStyle
    @Composable
    get() = mainText.copy(color = VisifyTheme.colors.label.error)

val VisifyTypography.miniError: TextStyle
    @Composable
    get() = mini.copy(color = VisifyTheme.colors.label.error)

val VisifyTypography.microError: TextStyle
    @Composable
    get() = micro.copy(color = VisifyTheme.colors.label.error)

val VisifyTypography.subheaderError: TextStyle
    @Composable
    get() = subHeader.copy(color = VisifyTheme.colors.label.error)