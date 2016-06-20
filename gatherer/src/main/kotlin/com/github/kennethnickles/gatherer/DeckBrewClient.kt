package com.github.kennethnickles.gatherer

import com.github.kennethnickles.gatherer.card.Card
import com.github.kennethnickles.gatherer.server.DeckBrewService
import com.github.kennethnickles.gatherer.server.GathererRequest
import com.github.kennethnickles.gatherer.util.CardGsonFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * @author kenneth.nickles
 * @since 2016-06-06.
 */
class DeckBrewClient {

    companion object {
        val TAG: String = DeckBrewClient::class.java.simpleName
    }

    private val mService: DeckBrewService

    constructor() {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.deckbrew.com/")
                .addConverterFactory(GsonConverterFactory.create(CardGsonFactory().createCardGson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        mService = retrofit.create(DeckBrewService::class.java)
    }

    fun cards(request: GathererRequest): Observable<List<Card>> {
        return mService.cards(request.getDeckBrewParams())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun card(id: String): Observable<Card> {
        return mService.card(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun typeahead(query: String): Observable<List<Card>>? {
        return mService.typeahead(query)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun sets(): Observable<List<String>> {
        return mService.sets()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun types(): Observable<List<String>> {
        return mService.types()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun supertypes(): Observable<List<String>> {
        return mService.supertypes()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun subtypes(): Observable<List<String>> {
        return mService.subtypes()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun colors(): Observable<List<String>> {
        return mService.colors()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }
}