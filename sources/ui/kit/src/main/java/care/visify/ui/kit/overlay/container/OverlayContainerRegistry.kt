package care.visify.ui.kit.overlay.container


object OverlayContainerRegistry {

    private val storage = mutableSetOf<OverlayContainerState>()

    fun register(state: OverlayContainerState): OverlayContainerState {
        storage.add(state)
        return state
    }

    fun unregister(state: OverlayContainerState): OverlayContainerState {
        storage.remove(state)
        return state
    }

    fun collect(): List<OverlayContainerState> = storage.toList()
}