package care.visify.api.models.address


data class CreateAddressForOrgRequest(
    val cityId: Int,
    val addressString: String,
    val details: String
)