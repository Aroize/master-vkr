package care.visify.core.navigator.api

import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes
import care.visify.core.navigator.api.bottomnav.BottomNav

abstract class VisifyScreen {
    @AnimRes
    @AnimatorRes
    var animEnter: Int? = null

    @AnimRes
    @AnimatorRes
    var animOut: Int? = null

    @AnimRes
    @AnimatorRes
    var popAnimEnter: Int? = null

    @AnimRes
    @AnimatorRes
    var popAnimOut: Int? = null

    open val bottomNav: BottomNav = BottomNav.Hide
}