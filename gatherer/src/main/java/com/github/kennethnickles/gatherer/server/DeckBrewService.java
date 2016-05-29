package com.github.kennethnickles.gatherer.server;

import com.github.kennethnickles.gatherer.card.Card;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @author kenneth.nickles
 * @since 2016-04-03.
 */
public interface DeckBrewService {

    @GET("/mtg/cards")
    Call<List<Card>> cards(@QueryMap Map<String, String> options);

    @GET("/mtg/cards")
    Call<Card> card(@Query("multiverseid") String id);

    @GET("/mtg/cards/typeahead")
    Call<List<Card>> typeahead(@Query("query") String query);

    @GET("/mtg/sets")
    Call<List<String>> sets();

    @GET("/mtg/types")
    Call<List<String>> types();

    @GET("/mtg/supertypes")
    Call<List<String>> supertypes();

    @GET("/mtg/subtypes")
    Call<List<String>> subtypes();

    // NOTE: They fucked up and don't include colorless
    @GET("/mtg/colors")
    Call<List<String>> colors();
}
