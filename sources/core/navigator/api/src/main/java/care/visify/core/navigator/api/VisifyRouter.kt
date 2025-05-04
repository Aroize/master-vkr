package care.visify.core.navigator.api

interface VisifyRouter {
    suspend fun navigateTo(screen: VisifyScreen)
    suspend fun navigateTo(screen: VisifyScreen, animation: VisifyNavAnimation)
    suspend fun newRootScreen(screen: VisifyScreen)
    suspend fun replaceScreen(screen: VisifyScreen)
    suspend fun backTo(screen: VisifyScreen?)

    suspend fun backToLastNotAuth()
    suspend fun newChain(vararg screens: VisifyScreen)
    suspend fun newRootChain(vararg screens: VisifyScreen)
    suspend fun finishChain()
    suspend fun exit()
}