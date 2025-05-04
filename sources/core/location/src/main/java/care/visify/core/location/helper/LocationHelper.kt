package care.visify.core.location.helper

import care.visify.core.location.model.Location

interface LocationHelper {
    fun hasPermissions(): Boolean
    suspend fun requireLocation(): Location?
    suspend fun ensurePermissions()
}