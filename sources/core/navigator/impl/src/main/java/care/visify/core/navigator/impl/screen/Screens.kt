package care.visify.core.navigator.impl.screen

import care.visify.core.navigator.api.VisifyFragmentScreen
import care.visify.core.navigator.api.VisifyScreen

val SCREEN_MAP: MutableMap<Class<*>, (VisifyScreen) -> VisifyFragmentScreen> = mutableMapOf()