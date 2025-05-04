package care.visify.api.models.chatting

import com.google.gson.annotations.SerializedName

class CountersResponse(
    @SerializedName("chats")
    val chats: Int,
    @SerializedName("responses")
    val responses: Int
)