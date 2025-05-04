package care.visify.api.models.chatting

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

class AttachmentDeserializer : JsonDeserializer<AttachmentResponse>, JsonSerializer<AttachmentRequest> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): AttachmentResponse {
        val jsonObject = json.asJsonObject
        val type = jsonObject["type"].asString
        val data = jsonObject["data"].asJsonObject

        return when (type) {
            AttachmentType.PHOTO -> context.deserialize<AttachmentResponse.PhotoAttachmentResponse>(data)
            AttachmentType.ORDER_CHANGES -> context.deserialize<AttachmentResponse.OrderChangesAttachmentResponse>(data)
            AttachmentType.VISIT -> context.deserialize<AttachmentResponse.VisitAttachmentResponse>(data)
            AttachmentType.SERVICE_MSG -> context.deserialize<AttachmentResponse.ServiceAttachmentResponse>(data)
            else -> throw IllegalArgumentException("Unsupported attachment type = $type")
        }
    }

    override fun serialize(
        src: AttachmentRequest,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement {
        val json = JsonObject()

        val (data, type) = when (src) {
            is AttachmentRequest.OrderChangesAttachmentRequest
                -> context.serialize(src, AttachmentRequest.OrderChangesAttachmentRequest::class.java) to AttachmentType.ORDER_CHANGES
            is AttachmentRequest.OrderRequestAttachmentRequest
                -> context.serialize(src, AttachmentRequest.OrderRequestAttachmentRequest::class.java) to AttachmentType.VISIT
            is AttachmentRequest.ImageAttachmentRequest
                -> context.serialize(src, AttachmentRequest.ImageAttachmentRequest::class.java) to AttachmentType.PHOTO
        }

        return json.apply {
            add("type", JsonPrimitive(type))
            add("data", data)
        }
    }
}

private inline fun<reified T> JsonDeserializationContext.deserialize(jsonObject: JsonObject): T
    = deserialize(jsonObject, T::class.java)