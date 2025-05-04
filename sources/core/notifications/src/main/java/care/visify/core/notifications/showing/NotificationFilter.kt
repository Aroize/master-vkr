package care.visify.core.notifications.showing

fun interface NotificationFilter {
    fun filter(notification: VisifyNotification): Boolean

    companion object {
        val NoOpFilter = NotificationFilter { true }
    }
}