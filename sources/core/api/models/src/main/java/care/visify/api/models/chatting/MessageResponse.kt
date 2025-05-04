package care.visify.api.models.chatting

import com.google.gson.annotations.SerializedName

data class MessageResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("cnvId")
    val cnvId: Long,
    @SerializedName("sender")
    val sender: ChatMemberResponse,
    @SerializedName("content")
    val content: MessageContentResponse,
    @SerializedName("createTs")
    val createTs: Long,
    @SerializedName("isRead")
    val isRead: Boolean
)