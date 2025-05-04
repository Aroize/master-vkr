package care.visify.core.navigator.impl

import care.visify.core.navigator.api.AuthVisifyScreen
import care.visify.core.navigator.api.VisifyNavAnimation
import care.visify.core.navigator.api.VisifyNavigatorTaskFactory
import care.visify.core.navigator.api.VisifyRouter
import care.visify.core.navigator.api.VisifyScreen
import care.visify.core.navigator.impl.screen.SCREEN_MAP
import care.visify.core.navigator.impl.screen.UsableFragmentScreen
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import java.util.Stack
import javax.inject.Inject

class VisifyRouterImpl @Inject constructor(
    private val delegate: Router,
    private val factory: VisifyNavigatorTaskFactory,
) : VisifyRouter {

    private val screenStack = Stack<VisifyScreen>()

    override suspend fun navigateTo(screen: VisifyScreen) {
        navigateTo(screen, VisifyNavAnimation.SlideLeftRight)
    }

    override suspend fun navigateTo(screen: VisifyScreen, animation: VisifyNavAnimation) {
        val exit = screenStack.peekNullable()
        screenStack.add(screen)

        preNavigationCall(exit, screen)
        delegate.navigateTo(toCiceroneScreen(screen, animation))
    }

    override suspend fun newRootScreen(screen: VisifyScreen) {
        val exit = screenStack.popNullable()
        screenStack.clear()
        screenStack.add(screen)

        preNavigationCall(exit, screen)
        delegate.newRootScreen(toCiceroneScreen(screen, VisifyNavAnimation.ChangeAlpha))
    }

    override suspend fun replaceScreen(screen: VisifyScreen) {
        val replaced = screenStack.popNullable()
        screenStack.add(screen)

        preNavigationCall(replaced, screen)
        delegate.replaceScreen(toCiceroneScreen(screen, VisifyNavAnimation.ChangeAlpha))
    }

    override suspend fun backTo(screen: VisifyScreen?) {
        val exit = screenStack.popNullable()
        val next = when {
            screen != null -> screenStack.popWhile { it != screen }
            else -> screenStack.firstOrNull()?.apply {
                screenStack.clear()
                screenStack.add(this)
            }
        }

        preNavigationCall(exit, next)
        delegate.backTo(screen?.let { toCiceroneScreen(it) })
    }

    override suspend fun backToLastNotAuth() {
        val exit = screenStack.popNullable()
        val lastNotAuthScreen = screenStack.popWhile { it is AuthVisifyScreen }

        preNavigationCall(exit, lastNotAuthScreen)
        delegate.backTo(lastNotAuthScreen?.let { toCiceroneScreen(it) })
    }

    override suspend fun newChain(vararg screens: VisifyScreen) {
        val exit = screenStack.peekNullable()
        val actual = screens.lastOrNull()

        screenStack.addAll(screens)

        preNavigationCall(exit, actual)
        delegate.newChain(
            *screens.map { toCiceroneScreen(it, VisifyNavAnimation.ChangeAlpha) }
                .toTypedArray()
        )
    }

    override suspend fun newRootChain(vararg screens: VisifyScreen) {
        val exit = screenStack.popNullable()
        val actual = screens.lastOrNull()

        screenStack.clear()
        screenStack.addAll(screens)

        preNavigationCall(exit, actual)
        delegate.newRootChain(
            *screens.map { toCiceroneScreen(it, VisifyNavAnimation.ChangeAlpha) }
                .toTypedArray()
        )
    }

    override suspend fun finishChain() {
        val exit = screenStack.popNullable()
        preNavigationCall(exit, null)
        delegate.finishChain()
    }

    override suspend fun exit() {
        val exit = screenStack.popNullable()
        val next = screenStack.peekNullable()
        preNavigationCall(exit, next)
        delegate.exit()
    }

    private suspend fun preNavigationCall(
        prev: VisifyScreen?,
        next: VisifyScreen?,
    ) {

        val tasks = factory.collect()
        tasks.forEach { it.block(prev, next) }
    }

    private fun toCiceroneScreen(visify: VisifyScreen, anim: VisifyNavAnimation): FragmentScreen {
        when (anim) {
            VisifyNavAnimation.SlideLeftRight -> {
                visify.animEnter = R.anim.enter_from_right
                visify.animOut = R.anim.exit_to_left
                visify.popAnimEnter = R.anim.enter_from_left
                visify.popAnimOut = R.anim.exit_to_right
            }

            VisifyNavAnimation.SlideBottomTop -> {
                visify.animEnter = R.anim.enter_from_bottom
                visify.animOut = R.anim.exit_to_top
                visify.popAnimEnter = R.anim.enter_from_top
                visify.popAnimOut = R.anim.exit_to_bottom
            }

            VisifyNavAnimation.ChangeAlpha -> {
                visify.animEnter = R.animator.fade_in
                visify.animOut = R.animator.fade_out
            }
        }
        return toCiceroneScreen(visify)
    }

    private fun toCiceroneScreen(visify: VisifyScreen): FragmentScreen {
        val handler = SCREEN_MAP[visify::class.java]
        handler ?: throw IllegalStateException("Can't find handler for screen $visify")
        val screen = UsableFragmentScreen(visify) {
            handler.invoke(visify).factory.invoke()
        }

        return screen
    }

    private inline fun <T> Stack<T>.popWhile(predicate: (T) -> Boolean): T? {
        var curr = peekNullable()
        while (curr != null && predicate(curr)) {
            curr = peekNullable()
            popNullable()
        }
        return curr
    }

    private fun <T> Stack<T>.peekNullable(): T? {
        if (isEmpty()) return null
        return peek()
    }

    private fun <T> Stack<T>.popNullable(): T? {
        if (isEmpty()) return null
        return pop()
    }
}