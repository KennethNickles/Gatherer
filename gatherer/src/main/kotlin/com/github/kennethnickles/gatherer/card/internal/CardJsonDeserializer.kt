package com.github.kennethnickles.gatherer.card.internal

import com.github.kennethnickles.gatherer.card.Card
import com.github.kennethnickles.gatherer.card.Edition
import com.github.kennethnickles.gatherer.card.Format
import com.github.kennethnickles.gatherer.card.Rule
import com.github.kennethnickles.gatherer.card.Symbol
import com.github.kennethnickles.gatherer.util.GsonUtils
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.util.ArrayList

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
            for (supertype in jsonObject.get("supertypes").asJsonArray) {
                builder.withSupertype(supertype.asString)
            }
        }
        if (GsonUtils.isNonNull(jsonObject.get("types"))) {
            for (type in jsonObject.get("types").asJsonArray) {
                builder.withType(type.asString)
            }
        }
        if (GsonUtils.isNonNull(jsonObject.get("subtypes"))) {
            for (subtype in jsonObject.get("subtypes").asJsonArray) {
                builder.withSubtype(subtype.asString)
            }
        }
        if (GsonUtils.isNonNull(jsonObject.get("cmc"))) {
            builder.withConvertedManaCost(jsonObject.get("cmc").asInt)
        }
        if (GsonUtils.isNonNull(jsonObject.get("cost"))) {
            builder.withSymbols(
                    context.deserialize<ArrayList<Symbol>>(jsonObject.get("cost"), SymbolListTokenType().type))
        }
        if (GsonUtils.isNonEmpty(jsonObject.get("power"))) {
            builder.withPower(jsonObject.get("power").asString)
        }
        if (GsonUtils.isNonEmpty(jsonObject.get("toughness"))) {
            builder.withToughness(jsonObject.get("toughness").asString)
        }
        if (GsonUtils.isNonNull(jsonObject.get("formats"))) {
            for (entry in jsonObject.get("formats").asJsonObject.entrySet()) {
                builder.withFormat(getFormat(entry.key), entry.value.asString)
            }
        }
        if (GsonUtils.isNonNull(jsonObject.get("text"))) {
            builder.withRules(context.deserialize<ArrayList<Rule>>(jsonObject.get("text"), RuleListTokenType().type))
        }
        if (GsonUtils.isNonNull(jsonObject.get("editions"))) {
            for (edition in jsonObject.getAsJsonArray("editions")) {
                builder.withEdition(context.deserialize<Edition>(edition, Edition::class.java))
            }
        }
        return builder.build()
    }

    private fun getFormat(format: String): Format {
        if ("block".equals(format)) {
            return Format.BLOCK
        }
        if ("standard".equals(format)) {
            return Format.STANDARD
        }
        if ("modern".equals(format)) {
            return Format.MODERN
        }
        if ("commander".equals(format)) {
            return Format.COMMANDER
        }
        if ("legacy".equals(format)) {
            return Format.LEGACY
        }
        if ("vintage".equals(format)) {
            return Format.VINTAGE
        }
        if ("booster_draft".equals(format)) {
            return Format.BOOSTER_DRAFT
        }
        if ("sealed_deck".equals(format)) {
            return Format.SEALED_DECK
        }
        if ("two_headed_giant".equals(format)) {
            return Format.TWO_HEADED_GIANT
        }
        if ("team_unified_construct".equals(format)) {
            return Format.TEAM_UNIFIED_CONSTRUCT
        }
        if ("team_limited".equals(format)) {
            return Format.TEAM_LIMITED
        }
        if ("team_booster_draft".equals(format)) {
            return Format.TEAM_BOOSTER_DRAFT
        }
        if ("team_sealed_draft".equals(format)) {
            return Format.TEAM_SEALED_DRAFT
        }
        return Format.UNKNOWN
    }
}