package com.github.kennethnickles.gatherer.util

import com.github.kennethnickles.gatherer.card.Card
import com.github.kennethnickles.gatherer.card.Edition
import com.github.kennethnickles.gatherer.card.Rules
import com.github.kennethnickles.gatherer.card.Symbols
import com.google.gson.Gson
import com.google.gson.GsonBuilder

/**
 * @author kenneth.nickles
 * @since 2016-06-06.
 */
class CardGsonFactory {

    private val cardDeserializer = Card.Deserializer()
    private val rulesDeserializer = Rules.Deserializer()
    private val symbolsDeserializer = Symbols.Deserializer()
    private val editionDeserializer = Edition.Deserializer()

    fun createCardGson(): Gson {
        return GsonBuilder()
                .registerTypeAdapter(Card::class.java, cardDeserializer)
                .registerTypeAdapter(Rules::class.java, rulesDeserializer)
                .registerTypeAdapter(Symbols::class.java, symbolsDeserializer)
                .registerTypeAdapter(Edition::class.java, editionDeserializer)
                .create()
    }
}