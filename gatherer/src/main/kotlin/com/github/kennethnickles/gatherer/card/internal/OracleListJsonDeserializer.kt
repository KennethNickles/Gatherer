package com.github.kennethnickles.gatherer.card.internal

import com.github.kennethnickles.gatherer.card.Oracle
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.util.ArrayList

/**
 * @author kenneth.nickles
 * @since 2016-06-25.
 */
class OracleListJsonDeserializer : JsonDeserializer<ArrayList<Oracle>> {

	@Throws(JsonParseException::class)
	override fun deserialize(json: JsonElement, typeOfT: java.lang.reflect.Type,
							 context: JsonDeserializationContext): ArrayList<Oracle> {
		val text = json.asString
		val lines = text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
		val oracles: ArrayList<Oracle> = ArrayList()
		for (i in lines.indices) {
			val oracleText = Oracle.from(lines[i]) ?: continue
			oracles.add(oracleText)
		}
		return oracles
	}
}
