package care.visify.core.notifications.showing


interface NotificationShowingProxy {
    fun clearFilter()
    fun setShowingFilter(filter: NotificationFilter)
    fun sendNotification(notification: VisifyNotification)
}

