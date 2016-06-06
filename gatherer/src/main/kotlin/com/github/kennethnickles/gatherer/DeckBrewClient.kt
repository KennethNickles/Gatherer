package com.github.kennethnickles.gatherer

import com.github.kennethnickles.gatherer.card.Card
import com.github.kennethnickles.gatherer.server.DeckBrewService
import com.github.kennethnickles.gatherer.server.GathererRequest
import com.github.kennethnickles.gatherer.util.CardGsonFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable

/**
 * @author kenneth.nickles
 * @since 2016-06-06.
 *
 * TODO: Wrap in rx
 */
class DeckBrewClient {

    companion object {
        val TAG: String = DeckBrewClient::class.java.simpleName
    }

    private val mService: DeckBrewService

    constructor() {
        val mRetrofit = Retrofit.Builder().baseUrl("https://api.deckbrew.com/")
                .addConverterFactory(GsonConverterFactory.create(CardGsonFactory().createCardGson())).build()
        mService = mRetrofit.create(DeckBrewService::class.java)
    }

    fun cards(request: GathererRequest): Observable<List<Card>> {
        throw UnsupportedOperationException()
    }

    fun card(id: String): Observable<Card> {
        throw UnsupportedOperationException()
    }

    fun typeahead(query: String): Observable<List<Card>>? {
        throw UnsupportedOperationException()
    }

    fun sets(): Observable<List<String>> {
        throw UnsupportedOperationException()
    }

    fun types(): Observable<List<String>> {
        throw UnsupportedOperationException()
    }

    fun supertypes(): Observable<List<String>> {
        throw UnsupportedOperationException()
    }

    fun subtypes(): Observable<List<String>> {
        throw UnsupportedOperationException()
    }

    fun colors(): Observable<List<String>> {
        throw UnsupportedOperationException()
    }
}