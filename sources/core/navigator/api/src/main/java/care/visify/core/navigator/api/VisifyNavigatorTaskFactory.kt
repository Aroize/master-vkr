package care.visify.core.navigator.api

interface VisifyNavigatorTaskFactory {
    fun collect(): List<VisifyNavigatorBlockingTask>
}