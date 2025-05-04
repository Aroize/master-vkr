package care.visify.ui.models.feedback

import androidx.annotation.StringRes
import care.visify.core.image.Image
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class SendFeedbackUiModel(
    @StringRes
    val titleResId: Int = -1,
    val logo: Image = Image(),
    val favor: String = "",
    val date: String = "",
    val master: String = "",
    val rating: Int = 0,
    val review: String = "",
    val photos: PersistentList<Image> = persistentListOf(),
) {
    val isSendingEnable
        get() = rating > 0 && (review.isNotBlank() || photos.isNotEmpty())
}