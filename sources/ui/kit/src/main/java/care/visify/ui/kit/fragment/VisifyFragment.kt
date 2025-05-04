package care.visify.ui.kit.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import care.visify.ui.kit.R
import care.visify.ui.kit.theme.VisifyTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
abstract class VisifyFragment: Fragment() {

    @Inject
    internal lateinit var compositionProvider: CompositionProvider

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.visify_ui_kit_fragment_composable,
            container,
            false
        )
        val composeView = view.findViewById<ComposeView>(R.id.compose_view)
        composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                VisifyTheme(providedValues = compositionProvider.provideCompositions()) {
                    FragmentContent()
                }
            }
        }
        return view
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        when (nextAnim) {
            R.anim.enter_from_left -> view?.z = Z_INDEX_BACKGROUND
            R.anim.enter_from_right -> view?.z = Z_INDEX_FOREGROUND
            R.anim.exit_to_right -> view?.z = Z_INDEX_FOREGROUND
            R.anim.exit_to_left -> view?.z = Z_INDEX_BACKGROUND
            R.anim.enter_from_bottom -> view?.z = Z_INDEX_FOREGROUND
            R.anim.exit_to_bottom -> view?.z = Z_INDEX_FOREGROUND
            R.anim.exit_to_top -> view?.z = Z_INDEX_BACKGROUND
            R.anim.enter_from_top -> view?.z = Z_INDEX_BACKGROUND
        }
        return super.onCreateAnimation(transit, enter, nextAnim)
    }

    @Composable
    protected abstract fun FragmentContent()

    private companion object {
        const val Z_INDEX_FOREGROUND = 100F
        const val Z_INDEX_BACKGROUND = 0F
    }
}