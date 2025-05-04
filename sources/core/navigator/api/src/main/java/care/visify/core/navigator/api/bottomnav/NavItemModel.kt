package care.visify.core.navigator.api.bottomnav

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class NavItemModel(
    @DrawableRes val iconRes: Int,
    @StringRes val textRes: Int
)