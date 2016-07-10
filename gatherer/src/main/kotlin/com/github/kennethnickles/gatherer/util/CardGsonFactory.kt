package com.github.kennethnickles.gatherer.util

import com.github.kennethnickles.gatherer.card.Card
import com.github.kennethnickles.gatherer.card.Edition
import com.github.kennethnickles.gatherer.card.Set
import com.github.kennethnickles.gatherer.card.internal.CardJsonDeserializer
import com.github.kennethnickles.gatherer.card.internal.EditionJsonDeserializer
import com.github.kennethnickles.gatherer.card.internal.RuleArrayJsonDeserializer
import com.github.kennethnickles.gatherer.card.internal.RuleArrayTokenType
import com.github.kennethnickles.gatherer.card.internal.RuleListJsonDeserializer
import com.github.kennethnickles.gatherer.card.internal.RuleListTokenType
import com.github.kennethnickles.gatherer.card.internal.SetJsonDeserializer
import com.github.kennethnickles.gatherer.card.internal.SymbolArrayJsonDeserializer
import com.github.kennethnickles.gatherer.card.internal.SymbolArrayTokenType
import com.github.kennethnickles.gatherer.card.internal.SymbolListJsonDeserializer
import com.github.kennethnickles.gatherer.card.internal.SymbolListTokenType
import com.google.gson.Gson
import com.google.gson.GsonBuilder

/**
 * @author kenneth.nickles
 * @since 2016-06-06.
 */
class CardGsonFactory {

	private val cardDeserializer = CardJsonDeserializer()
	private val editionDeserializer = EditionJsonDeserializer()
	private val ruleArrayDeserializer = RuleArrayJsonDeserializer()
	private val ruleListDeserializer = RuleListJsonDeserializer()
	private val setsDeserializer = SetJsonDeserializer()
	private val symbolArrayDeserializer = SymbolArrayJsonDeserializer()
	private val symbolListDeserializer = SymbolListJsonDeserializer()

	fun createCardGson(): Gson {
		return GsonBuilder()
				.registerTypeAdapter(Card::class.java, cardDeserializer)
				.registerTypeAdapter(Edition::class.java, editionDeserializer)
				.registerTypeAdapter(RuleArrayTokenType().type, ruleArrayDeserializer)
				.registerTypeAdapter(RuleListTokenType().type, ruleListDeserializer)
				.registerTypeAdapter(Set::class.java, setsDeserializer)
				.registerTypeAdapter(SymbolArrayTokenType().type, symbolArrayDeserializer)
				.registerTypeAdapter(SymbolListTokenType().type, symbolListDeserializer)
				.create()
	}
}
