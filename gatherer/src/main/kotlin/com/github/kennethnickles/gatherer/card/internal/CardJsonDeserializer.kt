package com.github.kennethnickles.gatherer.card.internal

import com.github.kennethnickles.gatherer.card.Card
import com.github.kennethnickles.gatherer.card.Edition
import com.github.kennethnickles.gatherer.card.Format
import com.github.kennethnickles.gatherer.card.Rules
import com.github.kennethnickles.gatherer.card.Symbols
import com.github.kennethnickles.gatherer.util.GsonUtils
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException

/**
 * @author kenneth.nickles
 * @since 2016-06-25.
 */
class CardJsonDeserializer : JsonDeserializer<Card> {
    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: java.lang.reflect.Type,
                             context: JsonDeserializationContext): Card {
        val builder = Card.builder()
        val jsonObject = json.asJsonObject
        if (GsonUtils.isNonNull(jsonObject.get("name"))) {
            builder.withName(jsonObject.get("name").asString)
        }
        if (GsonUtils.isNonNull(jsonObject.get("id"))) {
            builder.withId(jsonObject.get("id").asString)
        }
        if (GsonUtils.isNonEmpty(jsonObject.get("url"))) {
            builder.withUrl(jsonObject.get("url").asString)
        }
        if (GsonUtils.isNonEmpty(jsonObject.get("store_url"))) {
            builder.withStoreUrl(jsonObject.get("store_url").asString)
        }
        if (GsonUtils.isNonNull(jsonObject.get("supertypes"))) {
            builder.withSupertypes(jsonObject.get("supertypes").asJsonArray)
        }
        if (GsonUtils.isNonNull(jsonObject.get("types"))) {
            builder.withTypes(jsonObject.get("types").asJsonArray)
        }
        if (GsonUtils.isNonNull(jsonObject.get("subtypes"))) {
            builder.withSubtypes(jsonObject.get("subtypes").asJsonArray)
        }
        if (GsonUtils.isNonNull(jsonObject.get("cmc"))) {
            builder.withConvertedManaCost(jsonObject.get("cmc").asInt)
        }
        if (GsonUtils.isNonNull(jsonObject.get("cost"))) {
            builder.withSymbols(context.deserialize<Any>(jsonObject.get("cost"), Symbols::class.java) as Symbols)
        }
        if (GsonUtils.isNonEmpty(jsonObject.get("power"))) {
            builder.withPower(jsonObject.get("power").asString)
        }
        if (GsonUtils.isNonEmpty(jsonObject.get("toughness"))) {
            builder.withToughness(jsonObject.get("toughness").asString)
        }
        if (GsonUtils.isNonNull(jsonObject.get("formats"))) {
            for (format in Format.values()) {
                builder.withFormat(jsonObject.get("formats").asJsonObject)
            }
        }
        if (GsonUtils.isNonNull(jsonObject.get("text"))) {
            builder.withRules(context.deserialize<Any>(jsonObject.get("text"), Rules::class.java) as Rules)
        }
        if (GsonUtils.isNonNull(jsonObject.get("editions"))) {
            for (edition in jsonObject.getAsJsonArray("editions")) {
                builder.withEdition(context.deserialize<Any>(edition, Edition::class.java) as Edition)
            }
        }
        return builder.build()
    }
}