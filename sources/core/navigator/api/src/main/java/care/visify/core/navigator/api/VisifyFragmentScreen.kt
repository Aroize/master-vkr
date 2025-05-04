package care.visify.core.navigator.api

import androidx.fragment.app.Fragment

class VisifyFragmentScreen(
    val screen: VisifyScreen,
    val factory: () -> Fragment
)