package care.visify.api.models.chatting

import com.google.gson.annotations.SerializedName

data class ChatResponse(
    @SerializedName("chatId")
    val chatId: Int,
    @SerializedName("cnvId")
    val cnvId: Long,
    @SerializedName("me")
    val me: ChatMemberResponse,
    @SerializedName("otherMember")
    val otherMember: ChatMemberResponse,
    @SerializedName("lastMsg")
    val lastMsg: MessageResponse,
    @SerializedName("unreadCount")
    val unreadCount: Int
)