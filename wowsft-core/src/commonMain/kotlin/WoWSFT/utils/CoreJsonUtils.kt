package WoWSFT.utils

import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject

object CoreJsonUtils {
    val gameParamsJson = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
        isLenient = true
    }

    inline fun <reified T> decodeRaw(value: Any?): T {
        return gameParamsJson.decodeFromJsonElement(value.toJsonElement())
    }

    inline fun <reified T> decodeElement(value: JsonElement): T {
        return gameParamsJson.decodeFromJsonElement(value)
    }

    fun Any?.toJsonObject(): JsonObject {
        return toJsonElement().jsonObject
    }

    fun Any?.toJsonElement(): JsonElement {
        return when (this) {
            null -> JsonNull
            is JsonElement -> this
            is Map<*, *> -> JsonObject(
                entries.associate { (key, value) ->
                    key.toString() to value.toJsonElement()
                }
            )
            is Iterable<*> -> JsonArray(map { it.toJsonElement() })
            is Array<*> -> JsonArray(map { it.toJsonElement() })
            is Boolean -> JsonPrimitive(this)
            is Number -> JsonPrimitive(this)
            is String -> JsonPrimitive(this)
            else -> throw SerializationException("Unsupported raw JSON value type: ${this::class}")
        }
    }

    fun JsonObject.withoutKeys(keys: Set<String>): JsonObject {
        return JsonObject(filterKeys { it !in keys })
    }
}
