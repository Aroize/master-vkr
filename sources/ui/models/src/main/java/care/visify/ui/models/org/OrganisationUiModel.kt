package care.visify.ui.models.org

import care.visify.core.image.Image

data class OrganisationUiModel(
    val id: Int,
    val name: String,
    val logo: Image,
    val favors: String,
    val address: String,
    val rating: Float,
    val totalRatings: Int,
    val avgBill: Int,
    val avatars: List<Image>,
    val commentText: String?,
    val operatingTime: String?,
    val isFavourite: Boolean
)