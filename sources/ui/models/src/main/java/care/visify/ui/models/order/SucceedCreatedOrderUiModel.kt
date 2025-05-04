package care.visify.ui.models.order

class SucceedCreatedOrderUiModel(
    val organisation: String,
    val time: String,
    val master: String,
    val favor: String,
    val notificationsEnabled: Boolean
) {
    companion object {
        val Unknown = SucceedCreatedOrderUiModel("","","","", false)
    }
}
