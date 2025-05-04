package care.visify.ui.models.user

import care.visify.core.image.Image

data class UserUiModel(
    val id: Int = 0,
    val name: String = "",
    val avatar: Image? = null,
    val surname: String = "",
    val contact: String? = null,
    val city: String = "",
)