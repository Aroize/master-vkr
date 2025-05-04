package care.visify.ui.kit.modal.common

data class QueryInputState(
    val query: String,
) {
    companion object {
        val Empty = QueryInputState("")
    }
}