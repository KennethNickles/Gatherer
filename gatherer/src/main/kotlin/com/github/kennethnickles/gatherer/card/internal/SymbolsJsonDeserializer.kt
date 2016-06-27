package com.github.kennethnickles.gatherer.card.internal

import com.github.kennethnickles.gatherer.card.Symbol
import com.github.kennethnickles.gatherer.card.Symbols
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.util.regex.Pattern

/**
 * @author kenneth.nickles
 * @since 2016-06-25.
 */
class SymbolsJsonDeserializer : JsonDeserializer<Symbols> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: java.lang.reflect.Type,
                             context: JsonDeserializationContext): Symbols {
        /**
         * "text": "When you cast Kozilek, the Great Distortion, if you have fewer than
         * seven cards in hand, draw cards equal to the difference.\nMenace\nDiscard a
         * card with converted mana cost X: Counter target spell with converted mana cost
         * X.",

         * "text": "Tap an untapped Cephalid you control: Tap target permanent
         * .\n{U}{U}{U}: Tap all creatures without flying.",
         */
        val regex = "\\{(.*?)\\}"
        val pattern = Pattern.compile(regex)
        val colors = json.asString
        val matcher = pattern.matcher(colors)
        val builder = Symbols.builder()
        while (matcher.find()) {
            val mana = Symbol.from(colors.substring(matcher.start(), matcher.end())) ?: continue
            builder.withSymbol(mana)
        }
        return builder.build()
    }
}