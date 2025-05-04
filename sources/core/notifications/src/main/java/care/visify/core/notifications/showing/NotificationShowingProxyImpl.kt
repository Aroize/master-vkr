package care.visify.core.notifications.showing

import care.visify.core.notifications.NotificationHelper
import javax.inject.Inject


class NotificationShowingProxyImpl @Inject constructor(
    private val notificationHelper: NotificationHelper
): NotificationShowingProxy {

    private var actualFilter = NotificationFilter.NoOpFilter

    override fun clearFilter() {
        actualFilter = NotificationFilter.NoOpFilter
    }

    override fun setShowingFilter(filter: NotificationFilter) {
        actualFilter = filter
    }

    override fun sendNotification(notification: VisifyNotification) {
        if (actualFilter.filter(notification)) {
            notificationHelper.showPushNotification(notification)
        }
    }
}