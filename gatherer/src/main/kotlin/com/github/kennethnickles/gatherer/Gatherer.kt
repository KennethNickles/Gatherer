package com.github.kennethnickles.gatherer

import com.github.kennethnickles.gatherer.card.Card
import com.github.kennethnickles.gatherer.server.DeckBrewService
import com.github.kennethnickles.gatherer.server.GathererRequest
import com.github.kennethnickles.gatherer.server.GathererService
import com.github.kennethnickles.gatherer.util.CardGsonFactory
import com.github.kennethnickles.gatherer.util.Preconditions
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author kenneth.nickles
 * *
 * @since 2015-06-27.
 * *
 * * Static singleton for doing simple requests to Deckbrew or Gatherer
 */
object Gatherer {

    /**
     * Hits DeckBrew Api
     */
    fun cards(request: GathererRequest, callback: Callback<List<Card>>) {
        Preconditions.checkArgument(request != null, "request")
        Preconditions.checkArgument(callback != null, "callback")
        val gson = CardGsonFactory().createCardGson()
        val retrofit = Retrofit.Builder().baseUrl("https://api.deckbrew.com/").addConverterFactory(
                GsonConverterFactory.create(gson)).build()
        val service = retrofit.create(DeckBrewService::class.java)
        val cards = service.cards(request.getDeckBrewParams())
        cards.enqueue(callback)
    }

    /**
     * Hits DeckBrew Api
     */
    fun card(id: String, callback: Callback<Card>) {
        Preconditions.checkArgument(id != null, "id")
        Preconditions.checkArgument(callback != null, "callback")
        val gson = CardGsonFactory().createCardGson()
        val retrofit = Retrofit.Builder().baseUrl("https://api.deckbrew.com/").addConverterFactory(
                GsonConverterFactory.create(gson)).build()

        val service = retrofit.create(DeckBrewService::class.java)
        val card = service.card(id)
        card.enqueue(callback)
    }

    /**
     * Hits DeckBrew Api
     */
    fun typeahead(query: String, callback: Callback<List<Card>>?) {
        Preconditions.checkArgument(query != null, "query")
        Preconditions.checkArgument(callback != null, "callback")
        val gson = CardGsonFactory().createCardGson()
        val retrofit = Retrofit.Builder().baseUrl("https://api.deckbrew.com/").addConverterFactory(
                GsonConverterFactory.create(gson)).build()

        val service = retrofit.create(DeckBrewService::class.java)
        val typeaheads = service.typeahead(query)
        typeaheads.enqueue(callback)

    }

    /**
     * Hits DeckBrew Api
     */
    fun sets(callback: Callback<List<String>>) {
        Preconditions.checkArgument(callback != null, "callback")
        val gson = CardGsonFactory().createCardGson()
        val retrofit = Retrofit.Builder().baseUrl("https://api.deckbrew.com/").addConverterFactory(
                GsonConverterFactory.create(gson)).build()

        val service = retrofit.create(DeckBrewService::class.java)
        val sets = service.sets()
        sets.enqueue(callback)
    }

    /**
     * Hits DeckBrew Api
     */
    fun types(callback: Callback<List<String>>) {
        Preconditions.checkArgument(callback != null, "callback")
        val gson = CardGsonFactory().createCardGson()
        val retrofit = Retrofit.Builder().baseUrl("https://api.deckbrew.com/").addConverterFactory(
                GsonConverterFactory.create(gson)).build()

        val service = retrofit.create(DeckBrewService::class.java)
        val types = service.types()
        types.enqueue(callback)
    }

    /**
     * Hits DeckBrew Api
     */
    fun supertypes(callback: Callback<List<String>>) {
        Preconditions.checkArgument(callback != null, "callback")
        val gson = CardGsonFactory().createCardGson()
        val retrofit = Retrofit.Builder().baseUrl("https://api.deckbrew.com/").addConverterFactory(
                GsonConverterFactory.create(gson)).build()

        val service = retrofit.create(DeckBrewService::class.java)
        val supertypes = service.supertypes()
        supertypes.enqueue(callback)
    }

    /**
     * Hits DeckBrew Api
     */
    fun subtypes(callback: Callback<List<String>>) {
        Preconditions.checkArgument(callback != null, "callback")
        val gson = CardGsonFactory().createCardGson()
        val retrofit = Retrofit.Builder().baseUrl("https://api.deckbrew.com/").addConverterFactory(
                GsonConverterFactory.create(gson)).build()

        val service = retrofit.create(DeckBrewService::class.java)
        val subtypes = service.subtypes()
        subtypes.enqueue(callback)
    }

    /**
     * Hits DeckBrew Api
     */
    fun colors(callback: Callback<List<String>>) {
        Preconditions.checkArgument(callback != null, "callback")
        val gson = CardGsonFactory().createCardGson()
        val retrofit = Retrofit.Builder().baseUrl("https://api.deckbrew.com/").addConverterFactory(
                GsonConverterFactory.create(gson)).build()

        val service = retrofit.create(DeckBrewService::class.java)
        val colors = service.colors()
        colors.enqueue(callback)
    }

    /**
     * Hits Gatherer Scraper TODO: HtmlScraperFactory
     */
    fun simple(request: GathererRequest, callback: Callback<List<Card>>) {
        Preconditions.checkArgument(request != null, "request")
        Preconditions.checkArgument(callback != null, "callback")
        val retrofit = Retrofit.Builder().baseUrl("http://gatherer.wizards.com/").build()
        val service = retrofit.create(GathererService::class.java)
        val cards = service.simple(request.getGathererParams())
        cards.enqueue(callback)
    }

    /**
     * Hits Gatherer Scraper TODO: HtmlScraperFactory
     */
    fun advanced(request: GathererRequest, callback: Callback<List<Card>>) {
        Preconditions.checkArgument(request != null, "request")
        Preconditions.checkArgument(callback != null, "callback")
        val retrofit = Retrofit.Builder().baseUrl("http://gatherer.wizards.com/").build()
        val service = retrofit.create(GathererService::class.java)
        val cards = service.advanced("advanced", request.getGathererParams())
        cards.enqueue(callback)
    }

    /**
     * Hits Gatherer Scraper TODO: HtmlScraperFactory
     */
    fun detail(multiverseId: String, callback: Callback<Card>) {
        Preconditions.checkArgument(multiverseId != null, "multiverseId")
        Preconditions.checkArgument(callback != null, "callback")
        val retrofit = Retrofit.Builder().baseUrl("http://gatherer.wizards.com/").build()
        val service = retrofit.create(GathererService::class.java)
        val card = service.detail(multiverseId)
        card.enqueue(callback)
    }

    /**
     * Hits Gatherer Scraper TODO: HtmlScraperFactory
     */
    fun random(callback: Callback<Card>) {
        Preconditions.checkArgument(callback != null, "callback")
        val retrofit = Retrofit.Builder().baseUrl("http://gatherer.wizards.com/").build()
        val service = retrofit.create(GathererService::class.java)
        val card = service.random("random")
        card.enqueue(callback)
    }
}
