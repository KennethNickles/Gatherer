package com.github.kennethnickles.gatherer.card.internal

import com.github.kennethnickles.gatherer.card.Symbol
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.util.ArrayList
import java.util.regex.Pattern

/**
 * @author kenneth.nickles
 * @since 2016-06-25.
 */
class SymbolArrayJsonDeserializer : JsonDeserializer<Array<Symbol>> {

	@Throws(JsonParseException::class)
	override fun deserialize(json: JsonElement, typeOfT: java.lang.reflect.Type,
							 context: JsonDeserializationContext): Array<Symbol> {

		val regex = "\\{(.*?)\\}"
		val pattern = Pattern.compile(regex)
		val colors = json.asString
		val matcher = pattern.matcher(colors)
		val symbols: ArrayList<Symbol> = ArrayList()
		while (matcher.find()) {
			val mana = Symbol.from(colors.substring(matcher.start(), matcher.end())) ?: continue
			symbols.add(mana)
		}
		return symbols.toTypedArray()
	}
}
