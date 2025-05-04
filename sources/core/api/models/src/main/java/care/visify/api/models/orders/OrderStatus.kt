package care.visify.api.models.orders

enum class OrderStatus {
    // market order statuses
    RESPONSES_PAUSED_BY_CLIENT,
    RESPONSES_STOPPED_BY_CLIENT,
    NOT_APPROVED,
    // visit statuses
    APPROVED_BY_CLIENT,
    APPROVED_BY_ORG,
    APPROVED_BY_CLIENT_AND_ORG,
    CANCELLED_BY_ORG,
    CANCELLED_BY_CLIENT,
    DONE;
}