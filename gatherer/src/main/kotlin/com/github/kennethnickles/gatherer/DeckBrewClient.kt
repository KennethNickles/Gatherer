package com.github.kennethnickles.gatherer

import android.util.Log
import com.github.kennethnickles.gatherer.card.Card
import com.github.kennethnickles.gatherer.card.Set
import com.github.kennethnickles.gatherer.server.DeckBrewService
import com.github.kennethnickles.gatherer.server.GathererRequest
import com.github.kennethnickles.gatherer.util.CardGsonFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
        val interceptor = HttpLoggingInterceptor();
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build();
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.deckbrew.com/")
                .addConverterFactory(GsonConverterFactory.create(CardGsonFactory().createCardGson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build()
        mService = retrofit.create(DeckBrewService::class.java)
    }

    fun cards(request: GathererRequest): Observable<List<Card>> {
        return mService.cards(request.getNames(),
                              request.getSupertypes(),
                              request.getTypes(),
                              request.getSubtypes(),
                              request.getColors())
                .doOnRequest { Log.d(TAG, "requesting: " + request.toString()) }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun card(id: String): Observable<Card> {
        return mService.card(id)
                .doOnRequest { Log.d(TAG, "requesting: " + id) }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun typeahead(q: String): Observable<List<Card>> {
        return mService.typeahead(q)
                .doOnRequest { Log.d(TAG, "requesting: " + q) }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun sets(): Observable<List<Set>> {
        return mService.sets()
                .doOnRequest { Log.d(TAG, "requesting: sets") }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun types(): Observable<List<String>> {
        return mService.types()
                .doOnRequest { Log.d(TAG, "requesting: types") }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun supertypes(): Observable<List<String>> {
        return mService.supertypes()
                .doOnRequest { Log.d(TAG, "requesting: supertypes") }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun subtypes(): Observable<List<String>> {
        return mService.subtypes()
                .doOnRequest { Log.d(TAG, "requesting: subtypes") }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun colors(): Observable<List<String>> {
        return mService.colors()
                .doOnRequest { Log.d(TAG, "requesting: colors") }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }
}