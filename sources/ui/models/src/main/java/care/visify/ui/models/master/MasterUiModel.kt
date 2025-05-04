package care.visify.ui.models.master

import care.visify.core.image.Image

data class MasterUiModel(
    val id: Int,
    val name: String,
    val surname: String?,
    val rating: Float,
    val profession: String,
    val avatar: Image
) {
    val title: String
        get() = "$name ${surname.orEmpty()}".trim()

    companion object {
        //fixme ?
        fun anyMaster(): MasterUiModel =
            MasterUiModel(
                id = -1,
                name = "Любой мастер",
                surname = null,
                rating = 0.0f,
                profession = "",
                avatar = Image()
            )
    }
}