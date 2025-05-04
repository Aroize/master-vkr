package care.visify.api.models.chatting

import com.google.gson.annotations.SerializedName

data class MessageContentResponse(
    @SerializedName("text")
    val text: String?,
    @SerializedName("attachments")
    val attachments: List<AttachmentResponse>
)