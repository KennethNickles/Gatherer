package com.github.kennethnickles.gatherer.server

import com.github.kennethnickles.gatherer.card.Card

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * @author kenneth.nickles
 * *
 * @since 2016-04-04.
 */
interface GathererService {

    @GET("/Pages/Search/Default.aspx")
    fun simple(@QueryMap options: Map<String, String>): Call<List<Card>>

    @GET("/Pages/Search/Default.aspx")
    fun advanced(@Query("action") action: String,
                 @QueryMap options: Map<String, String>): Call<List<Card>>

    @GET("/Pages/Card/Details.aspx")
    fun detail(@Query("multiverseid") id: String): Call<Card>

    @GET("/Pages/Card/Details.aspx")
    fun random(@Query("action") action: String): Call<Card>
}
