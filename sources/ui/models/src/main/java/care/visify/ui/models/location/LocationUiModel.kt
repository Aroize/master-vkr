package care.visify.ui.models.location

data class LocationUiModel(
    val lat: Float,
    val lon: Float,
    val operatingTime: String?,
    val distance: String?,
    val address: String,
) {

    fun isEmpty(): Boolean = lat.isNaN()
            && lon.isNaN()
            && operatingTime.isNullOrEmpty()
            && distance.isNullOrEmpty()
            && address.isEmpty()
}
