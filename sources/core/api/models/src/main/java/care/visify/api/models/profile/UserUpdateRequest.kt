package care.visify.api.models.profile

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.util.UUID

class UserUpdateRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("surname")
    val surname: String?,
    @SerializedName("avatarId")
    val avatarId: UUID?,
    @SerializedName("birthDateTs")
    val birth: LocalDateTime?,
    @SerializedName("sex")
    val sex: Int,
)