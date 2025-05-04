package care.visify.core.navigator.api

interface VisifyNavigatorBlockingTask {
    suspend fun block(
        prev: VisifyScreen?,
        next: VisifyScreen?
    )
}