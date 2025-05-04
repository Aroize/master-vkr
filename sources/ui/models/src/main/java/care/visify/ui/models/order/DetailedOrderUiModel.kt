package care.visify.ui.models.order

import care.visify.core.image.Image
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class DetailedOrderUiModel(
    val categoryName: String = "",
    val date: String = "",
    val time: String = "",
    val comment: String? = null,
    val masterName: String = "",
    val price: String = "",
    val referencesImages: PersistentList<Image> = persistentListOf(),

    val orgName: String = "",
    val orgAddress: String = "",
    val orgDistance: String? = "",
    val orgOperatingTime: String? = "",
    val orgLat: Float = Float.NaN,
    val orgLon: Float = Float.NaN,
)