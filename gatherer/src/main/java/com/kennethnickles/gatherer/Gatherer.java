package com.kennethnickles.gatherer;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.kennethnickles.gatherer.card.Card;
import com.kennethnickles.gatherer.parser.HtmlScraperFactory;
import com.kennethnickles.gatherer.server.GathererRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author kenneth.nickles
 * @since 2015-06-27.
 */
public class Gatherer {

    /**
     * Hits DeckBrew Api
     */
    public static void cards(@NonNull GathererRequest request, Callback<List<Card>> callback) {
        Preconditions.checkArgument(request != null, "GathererRequest");
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://deckbrew.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final DeckBrewService service = retrofit.create(DeckBrewService.class);
        final Call<List<Card>> cards = service.cards(request.getParams());
        cards.enqueue(callback);
    }

    /**
     * Hits DeckBrew Api
     */
    public static void card(@NonNull String id, Callback<Card> callback) {
        Preconditions.checkArgument(id != null, "id");
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://deckbrew.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final DeckBrewService service = retrofit.create(DeckBrewService.class);
        final Call<Card> card = service.card(id);
        card.enqueue(callback);
    }

    /**
     * Hits DeckBrew Api
     */
    public static void typeahead(@NonNull String query, Callback<List<Card>> callback) {
        Preconditions.checkArgument(query != null, "query");
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://deckbrew.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final DeckBrewService service = retrofit.create(DeckBrewService.class);
        final Call<List<Card>> typeaheads = service.typeahead(query);
        typeaheads.enqueue(callback);

    }

    /**
     * Hits DeckBrew Api
     */
    public static void sets(Callback<List<String>> callback) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://deckbrew.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final DeckBrewService service = retrofit.create(DeckBrewService.class);
        final Call<List<String>> sets = service.sets();
        sets.enqueue(callback);
    }

    /**
     * Hits DeckBrew Api
     */
    public static void types(Callback<List<String>> callback) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://deckbrew.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final DeckBrewService service = retrofit.create(DeckBrewService.class);
        final Call<List<String>> types = service.types();
        types.enqueue(callback);
    }

    /**
     * Hits DeckBrew Api
     */
    public static void supertypes(Callback<List<String>> callback) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://deckbrew.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final DeckBrewService service = retrofit.create(DeckBrewService.class);
        final Call<List<String>> supertypes = service.supertypes();
        supertypes.enqueue(callback);
    }

    /**
     * Hits DeckBrew Api
     */
    public static void subtypes(Callback<List<String>> callback) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://deckbrew.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final DeckBrewService service = retrofit.create(DeckBrewService.class);
        final Call<List<String>> subtypes = service.subtypes();
        subtypes.enqueue(callback);
    }

    /**
     * Hits DeckBrew Api
     */
    public static void colors(Callback<List<String>> callback) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://deckbrew.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final DeckBrewService service = retrofit.create(DeckBrewService.class);
        final Call<List<String>> colors = service.colors();
        colors.enqueue(callback);
    }

    /**
     * Hits Gatherer Scraper
     */
    public static void simple(@NonNull GathererRequest request, Callback<List<Card>> callback) {
        final Retrofit retrofit = new Retrofit.Builder().baseUrl("http://gatherer.wizards.com/")
                                                        .addConverterFactory(HtmlScraperFactory
                                                                                     .create())
                                                        .build();
        final GathererService service = retrofit.create(GathererService.class);
        final Call<List<Card>> cards = service.simple(request.getParams());
        cards.enqueue(callback);
    }

    /**
     * Hits Gatherer Scraper
     */
    public static void advanced(@NonNull GathererRequest request, Callback<List<Card>> callback) {
        final Retrofit retrofit = new Retrofit.Builder().baseUrl("http://gatherer.wizards.com/")
                                                        .addConverterFactory(HtmlScraperFactory
                                                                                     .create())
                                                        .build();
        final GathererService service = retrofit.create(GathererService.class);
        final Call<List<Card>> cards = service.advanced("advanced", request.getParams());
        cards.enqueue(callback);
    }

    /**
     * Hits Gatherer Scraper
     */
    public static void detail(@NonNull String multiverseId, Callback<Card> callback) {
        final Retrofit retrofit = new Retrofit.Builder().baseUrl("http://gatherer.wizards.com/")
                                                        .addConverterFactory(HtmlScraperFactory
                                                                                     .create())
                                                        .build();
        final GathererService service = retrofit.create(GathererService.class);
        final Call<Card> card = service.detail(multiverseId);
        card.enqueue(callback);
    }

    /**
     * Hits Gatherer Scraper
     */
    public static void random(Callback<Card> callback) {
        final Retrofit retrofit = new Retrofit.Builder().baseUrl("http://gatherer.wizards.com/")
                                                        .addConverterFactory(HtmlScraperFactory
                                                                                     .create())
                                                        .build();
        final GathererService service = retrofit.create(GathererService.class);
        final Call<Card> card = service.random("random");
        card.enqueue(callback);
    }
}
