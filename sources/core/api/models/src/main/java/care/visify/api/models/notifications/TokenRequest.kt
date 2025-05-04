package care.visify.api.models.notifications

import com.google.gson.annotations.SerializedName

class TokenRequest(@SerializedName("token") val token: String)