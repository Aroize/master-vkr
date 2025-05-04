package care.visify.core.image.formatter

import com.bumptech.glide.load.model.GlideUrl
import java.util.UUID

interface ImageUrlFormatter {
    fun formatPublic(id: UUID): GlideUrl
}
