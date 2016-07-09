package com.github.kennethnickles.gatherer.util

import com.github.kennethnickles.gatherer.card.Card
import com.github.kennethnickles.gatherer.card.Edition
import com.github.kennethnickles.gatherer.card.Set
import com.github.kennethnickles.gatherer.card.internal.CardJsonDeserializer
import com.github.kennethnickles.gatherer.card.internal.EditionJsonDeserializer
import com.github.kennethnickles.gatherer.card.internal.RuleListTokenType
import com.github.kennethnickles.gatherer.card.internal.RulesJsonDeserializer
import com.github.kennethnickles.gatherer.card.internal.SetJsonDeserializer
import com.github.kennethnickles.gatherer.card.internal.SymbolListTokenType
import com.github.kennethnickles.gatherer.card.internal.SymbolsJsonDeserializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder

/**
 * @author kenneth.nickles
 * @since 2016-06-06.
 */
class CardGsonFactory {

    private val cardDeserializer = CardJsonDeserializer()
    private val editionDeserializer = EditionJsonDeserializer()
    private val rulesDeserializer = RulesJsonDeserializer()
    private val setsDeserializer = SetJsonDeserializer()
    private val symbolsDeserializer = SymbolsJsonDeserializer()

    fun createCardGson(): Gson {
        return GsonBuilder()
                .registerTypeAdapter(Card::class.java, cardDeserializer)
                .registerTypeAdapter(Edition::class.java, editionDeserializer)
                .registerTypeAdapter(RuleListTokenType().type, rulesDeserializer)
                .registerTypeAdapter(Set::class.java, setsDeserializer)
                .registerTypeAdapter(SymbolListTokenType().type, symbolsDeserializer)
                .create()
    }
}