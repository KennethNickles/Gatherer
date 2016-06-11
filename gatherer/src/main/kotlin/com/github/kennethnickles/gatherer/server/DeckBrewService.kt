package com.github.kennethnickles.gatherer.server

import com.github.kennethnickles.gatherer.card.Card

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * @author kenneth.nickles
 * *
 * @since 2016-04-03.
 */
interface DeckBrewService {

    @GET("/mtg/cards")
    fun cards(@QueryMap options: Map<String, String>): Call<List<Card>>

    @GET("/mtg/cards")
    fun card(@Query("multiverseid") id: String): Call<Card>

    @GET("/mtg/cards/typeahead")
    fun typeahead(@Query("query") query: String): Call<List<Card>>

    @GET("/mtg/sets")
    fun sets(): Call<List<String>>

    @GET("/mtg/types")
    fun types(): Call<List<String>>

    @GET("/mtg/supertypes")
    fun supertypes(): Call<List<String>>

    @GET("/mtg/subtypes")
    fun subtypes(): Call<List<String>>

    // NOTE: They fucked up and don't include colorless
    @GET("/mtg/colors")
    fun colors(): Call<List<String>>
}
