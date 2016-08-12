package com.github.kennethnickles.gatherer.server

import com.github.kennethnickles.gatherer.card.Card
import com.github.kennethnickles.gatherer.card.Set
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * @author kenneth.nickles
 * *
 * @since 2016-04-03.
 */
interface DeckBrewService {

	@GET("/mtg/cards")
	fun cards(@Query("type") types: List<String>,
			  @Query("subtype") subtypes: List<String>,
			  @Query("supertype") supertypes: List<String>,
			  @Query("name") names: List<String>,
			  @Query("oracle") oracles: List<String>,
			  @Query("set") sets: List<String>,
			  @Query("rarity") rarities: List<String>,
			  @Query("color") colors: List<String>,
			  @Query("multicolor") bool: Boolean?,
			  @Query("format") formats: List<String>,
			  @Query("status") status: List<String>,
			  @Query("page") page: Int?): Observable<List<Card>>

	@GET("/mtg/cards")
	fun card(@Query("multiverseid") id: String): Observable<Card>

	@GET("/mtg/cards/typeahead")
	fun typeahead(@Query("q") q: String): Observable<List<Card>>

	@GET("/mtg/sets")
	fun sets(): Observable<List<Set>>

	@GET("/mtg/types")
	fun types(): Observable<List<String>>

	@GET("/mtg/supertypes")
	fun supertypes(): Observable<List<String>>

	@GET("/mtg/subtypes")
	fun subtypes(): Observable<List<String>>

	// NOTE: They fucked up and don't include colorless
	@GET("/mtg/colors")
	fun colors(): Observable<List<String>>
}
