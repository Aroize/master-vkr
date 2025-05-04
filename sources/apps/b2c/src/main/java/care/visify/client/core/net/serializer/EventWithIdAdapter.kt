package care.visify.client.core.net.serializer

import care.visify.core.api.sse.engine.handler.EventWithId
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type


class EventWithIdAdapter : JsonDeserializer<EventWithId> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): EventWithId {
        val jsonObject = json.asJsonObject

        val id = jsonObject["id"].asLong
        val type = jsonObject["type"].asString
        val eventElement = jsonObject["event"]

        val event: String = if (eventElement.isJsonObject) {
            eventElement.toString()
        } else {
            throw JsonParseException("Expected event to be a JSON object.")
        }

        return EventWithId(id, type, event)
    }
}
