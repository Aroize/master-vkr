package care.visify.core.location

import care.visify.core.location.model.Location


fun calculateDistance(
    current: Location,
    targetLat: Float,
    targetLon: Float,
): Float {
    return android.location.Location("current").apply {
        latitude = current.lat.toDouble()
        longitude = current.lon.toDouble()
    }.distanceTo(
        android.location.Location("target").apply {
            latitude = targetLat.toDouble()
            longitude = targetLon.toDouble()
        }
    ) / 1_000
}