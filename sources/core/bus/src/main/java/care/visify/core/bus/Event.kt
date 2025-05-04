package care.visify.core.bus

interface Event

// ========= begin region

data object OnAuthFailed: Event

data object OnAuthSucceed: Event

data object OnLogoutEvent: Event

data class NetworkStateEvent(val isNetworkAvailable: Boolean): Event

data class OrgToggledFavourite(
    val orgId: Int,
    val isFavourite: Boolean
): Event

data object MarketOrderCreated: Event

data object EmployeeOrgRoleExpired : Event

data class VisitChangesCreated(val visitId: Int) : Event
// ========= end region