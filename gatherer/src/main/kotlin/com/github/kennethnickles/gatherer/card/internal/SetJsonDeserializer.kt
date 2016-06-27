package com.github.kennethnickles.gatherer.card.internal

import com.github.kennethnickles.gatherer.card.Set
import com.github.kennethnickles.gatherer.util.GsonUtils
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

/**
 * @author kenneth.nickles
 * @since 2016-06-26.
 */
class SetJsonDeserializer : JsonDeserializer<Set> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Set {
        val builder = Set.builder()
        val jsonObject = json.asJsonObject

        if (GsonUtils.isNonNull(jsonObject.get("id"))) {
            builder.withId(jsonObject.get("id").asString)
        }
        if (GsonUtils.isNonNull(jsonObject.get("name"))) {
            builder.withName(jsonObject.get("name").asString)
        }
        if (GsonUtils.isNonNull(jsonObject.get("border"))) {
            builder.withBorder(jsonObject.get("border").asString)
        }
        if (GsonUtils.isNonNull(jsonObject.get("type"))) {
            builder.withType(jsonObject.get("type").asString)
        }
        if (GsonUtils.isNonNull(jsonObject.get("url"))) {
            builder.withUrl(jsonObject.get("url").asString)
        }
        if (GsonUtils.isNonNull(jsonObject.get("cards_url"))) {
            builder.withCardsUrl(jsonObject.get("cards_url").asString)
        }
        return builder.build()
    }
}