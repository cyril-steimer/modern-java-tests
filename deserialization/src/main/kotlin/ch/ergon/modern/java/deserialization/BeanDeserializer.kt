package ch.ergon.modern.java.deserialization

import ch.ergon.modern.java.api.bean.Bean
import ch.ergon.modern.java.api.bean.BeanProperty
import com.google.gson.JsonElement
import com.google.gson.JsonObject;
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.isSubclassOf

object BeanDeserializer {
    fun <T : Bean> deserialize(json: JsonObject, type: KClass<T>): T {
        val result = type.createInstance()
        for ((key, value) in json.entrySet()) {
            val property = result.getBeanProperty(key) ?: throw IllegalArgumentException("Unknown property '$key'")
            result[property] = deserialize(property, value)
        }
        return result
    }

    private fun deserialize(property: BeanProperty, jsonElement: JsonElement): Any? {
        return when {
            property.type == Double::class -> jsonElement.asDouble
            property.type == String::class -> jsonElement.asString
            property.type.isSubclassOf(Bean::class) -> deserialize(jsonElement.asJsonObject, property.type as KClass<Bean>)
            else -> throw IllegalArgumentException("Unsupported type for deserialization '${property.type}'")
        }
    }
}