package care.visify.api.models.chatting

import com.google.gson.annotations.SerializedName
import java.util.UUID

sealed interface AttachmentRequest {
    data class ImageAttachmentRequest(
        @SerializedName("photoId")
        val photo: UUID
    ): AttachmentRequest

    data class OrderChangesAttachmentRequest(
        @SerializedName("orderChangesId")
        val orderChangesId: Int
    ): AttachmentRequest

    data class OrderRequestAttachmentRequest(
        @SerializedName("orderRequestId")
        val orderRequestId: Int
    ): AttachmentRequest
}