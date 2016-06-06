package com.github.kennethnickles.gatherer

import com.github.kennethnickles.gatherer.card.Card
import com.github.kennethnickles.gatherer.server.GathererRequest
import com.github.kennethnickles.gatherer.server.GathererService
import retrofit2.Call
import retrofit2.Retrofit
import rx.Observable

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
        throw UnsupportedOperationException()
    }

    fun advanced(action: String?, request: GathererRequest): Observable<List<Card>> {
        throw UnsupportedOperationException()
    }

    fun detail(id: String?): Observable<Card> {
        throw UnsupportedOperationException()
    }

    fun random(action: String?): Observable<Card> {
        throw UnsupportedOperationException()
    }
}