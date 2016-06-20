package com.github.kennethnickles.gatherer.server

import com.github.kennethnickles.gatherer.card.Card
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap
import rx.Observable

/**
 * @author kenneth.nickles
 * *
 * @since 2016-04-04.
 */
interface GathererService {

    @GET("/Pages/Search/Default.aspx")
    fun simple(@QueryMap options: Map<String, String>): Observable<List<Card>>

    @GET("/Pages/Search/Default.aspx")
    fun advanced(@Query("action") action: String,
                 @QueryMap options: Map<String, String>): Observable<List<Card>>

    @GET("/Pages/Card/Details.aspx")
    fun detail(@Query("multiverseid") id: String): Observable<Card>

    @GET("/Pages/Card/Details.aspx")
    fun random(@Query("action") action: String): Observable<Card>
}
