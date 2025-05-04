package care.visify.api.models.chatting

import care.visify.api.models.common.ImageResponse
import care.visify.api.models.orders.OrderChangesStatus
import care.visify.api.models.orders.OrderStatus
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime


@Retention(AnnotationRetention.SOURCE)
annotation class AttachmentType {
    companion object {
        const val PHOTO = "photo"
        const val ORDER_CHANGES = "order_changes"
        const val VISIT = "visit"
        const val SERVICE_MSG = "service_msg"
    }
}



sealed interface AttachmentResponse {

    class ServiceAttachmentResponse(val msgType: String) : AttachmentResponse

    data class PhotoAttachmentResponse(
        @SerializedName("items")
        val items: List<ImageResponse>,
        @SerializedName("total")
        val total: Int
    ): AttachmentResponse

    data class OrderChangesAttachmentResponse(
        @SerializedName("orderChangesId")
        val orderChangesId: Int,

        @SerializedName("orderId")
        val orderId: Int,
        @SerializedName("responseId")
        val responseId: Int,
        @SerializedName("orgId")
        val orgId: Int,

        @SerializedName("employeeIdOld")
        val employeeIdOld: Int?,
        @SerializedName("employeeIdNew")
        val employeeIdNew: Int,

        @SerializedName("favorIdOld")
        val favorIdOld: Int?,
        @SerializedName("favorIdNew")
        val favorIdNew: Int,

        @SerializedName("orderDttmOld")
        val orderDttmOld: LocalDateTime?,
        @SerializedName("orderDttmNew")
        val orderDttmNew: LocalDateTime,

        @SerializedName("priceOld")
        val priceOld: Int?,
        @SerializedName("priceNew")
        val priceNew: Int,

        @SerializedName("durationMinutesOld")
        val durationMinutesOld: Int?,
        @SerializedName("durationMinutesNew")
        val durationMinutesNew: Int,

        @SerializedName("changesStatus")
        val changesStatus: OrderChangesStatus,

        @SerializedName("createdDttm")
        val createdDttm: LocalDateTime
    ): AttachmentResponse

    data class VisitAttachmentResponse(
        @SerializedName("orderId")
        val orderId: Int,

        @SerializedName("employeeId")
        val employeeId: Int,
        @SerializedName("favorId")
        val favorId: Int,
        @SerializedName("orgId")
        val orgId: Int,

        @SerializedName("createDttm")
        val createDttm: LocalDateTime,
        @SerializedName("orderDttm")
        val orderDttm: LocalDateTime,

        @SerializedName("orderComment")
        val orderComment: String?,
        @SerializedName("price")
        val price: Int,
        @SerializedName("priceCurrencyType")
        val priceCurrencyType: Int,
        @SerializedName("durationMinutes")
        val durationMinutes: Int,

        @SerializedName("references")
        val references: List<ImageResponse>,

        @SerializedName("isActive")
        val isActive: Boolean,
        @SerializedName("status")
        val status: OrderStatus,
    ): AttachmentResponse
}