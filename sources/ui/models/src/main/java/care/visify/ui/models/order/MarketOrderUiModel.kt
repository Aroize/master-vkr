package care.visify.ui.models.order

data class MarketOrderUiModel(
    val id: Int,
    val name: String,
    val date: String,
    val status: MarketOrderStatus,
    val creationDate: String,
    val isArchive: Boolean,
) {
    companion object {
        val Unknown = MarketOrderUiModel(0, "", "", MarketOrderStatus.CLOSED, "", true)
    }
}