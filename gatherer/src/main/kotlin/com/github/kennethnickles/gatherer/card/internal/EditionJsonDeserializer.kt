package com.github.kennethnickles.gatherer.card.internal

import com.github.kennethnickles.gatherer.card.Edition
import com.github.kennethnickles.gatherer.util.GsonUtils
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException

/**
 * @author kenneth.nickles
 * @since 2016-06-25.
 */
class EditionJsonDeserializer : JsonDeserializer<Edition> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: java.lang.reflect.Type,
                             context: JsonDeserializationContext): Edition {
        val builder = Edition.builder()
        val jsonObject = json.asJsonObject
        if (GsonUtils.isNonNull(jsonObject.get("set"))) {
            builder.withSet(jsonObject.get("set").asString)
        }
        if (GsonUtils.isNonNull(jsonObject.get("set_id"))) {
            builder.withSetId(jsonObject.get("set_id").asString)
        }
        if (GsonUtils.isNonNull(jsonObject.get("rarity"))) {
            builder.withRarity(jsonObject.get("rarity").asString)
        }
        if (GsonUtils.isNonNull(jsonObject.get("artist"))) {
            builder.withArtist(jsonObject.get("artist").asString)
        }
        if (GsonUtils.isNonNull(jsonObject.get("multiverse_id"))) {
            builder.withMultiverseId(jsonObject.get("multiverse_id").asInt)
        }
        if (GsonUtils.isNonNull(jsonObject.get("flavor"))) {
            builder.withFlavorText(jsonObject.get("flavor").asString)
        }
        if (GsonUtils.isNonEmpty(jsonObject.get("number"))) {
            builder.withNumber(jsonObject.get("number").asString)
        }
        if (GsonUtils.isNonNull(jsonObject.get("layout"))) {
            builder.withLayout(jsonObject.get("layout").asString)
        }
        if (GsonUtils.isNonNull(jsonObject.get("image_url"))) {
            builder.withImageUrl(jsonObject.get("image_url").asString)
        }
        if (GsonUtils.isNonNull(jsonObject.get("set_url"))) {
            builder.withSetUrl(jsonObject.get("set_url").asString)
        }
        if (GsonUtils.isNonNull(jsonObject.get("store_url"))) {
            builder.withStoreUrl(jsonObject.get("store_url").asString)
        }
        return builder.build()
    }
}