package com.github.kennethnickles.gatherer

import com.github.kennethnickles.gatherer.card.Card
import com.github.kennethnickles.gatherer.server.GathererRequest
import com.github.kennethnickles.gatherer.server.GathererService
import retrofit2.Retrofit
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * @author kenneth.nickles
 * @since 2016-06-06.
 */
class GathererClient {

    companion object {
        val TAG: String = GathererClient::class.java.simpleName
    }

    private val mService: GathererService

    constructor() {
        // TODO: HtmlScraperConverterFactory
        val mRetrofit = Retrofit.Builder().baseUrl("http://gatherer.wizards.com/").build()
        mService = mRetrofit.create(GathererService::class.java)
    }

    fun simple(request: GathererRequest): Observable<List<Card>> {
        return mService.simple(request.getDeckBrewParams())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun advanced(action: String, request: GathererRequest): Observable<List<Card>> {
        return mService.advanced(action, request.getGathererParams())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun detail(id: String): Observable<Card> {
        return mService.detail(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun random(action: String): Observable<Card> {
        return mService.random(action)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }
}