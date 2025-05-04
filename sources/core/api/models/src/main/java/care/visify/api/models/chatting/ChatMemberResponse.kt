package care.visify.api.models.chatting

import com.google.gson.annotations.SerializedName

enum class ChatMemberType { CLIENT, ORG }

data class ChatMemberResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("type")
    val type: ChatMemberType
) {
    val isOrg: Boolean
        get() = type == ChatMemberType.ORG
}