package care.visify.ui.models.feedback

import care.visify.core.image.Image
import java.time.LocalDateTime


data class AbsentReviewUiModel(
    val orderId: Int = 0,
    val orgId: Int = 0,
    val logo: Image = Image(),
    val date: LocalDateTime = LocalDateTime.now(),
    val favor: String = "",
    val master: String = ""
)