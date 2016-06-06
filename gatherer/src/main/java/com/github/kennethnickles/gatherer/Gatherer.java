package com.github.kennethnickles.gatherer;

import android.support.annotation.NonNull;
import com.github.kennethnickles.gatherer.card.Card;
import com.github.kennethnickles.gatherer.server.DeckBrewService;
import com.github.kennethnickles.gatherer.server.GathererRequest;
import com.github.kennethnickles.gatherer.server.GathererService;
import com.github.kennethnickles.gatherer.util.CardGsonFactory;
import com.github.kennethnickles.gatherer.util.Preconditions;
import com.google.gson.Gson;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author kenneth.nickles
 * @since 2015-06-27.
 *
 * Static singleton for doing simple requests to Deckbrew or Gatherer
 */
public class Gatherer {

    /**
     * Hits DeckBrew Api
     */
    public static void cards(@NonNull GathererRequest request, @NonNull Callback<List<Card>> callback) {
        Preconditions.checkArgument(request != null, "request");
        Preconditions.checkArgument(callback != null, "callback");
        final Gson gson = new CardGsonFactory().createCardGson();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.deckbrew.com/")
                                                        .addConverterFactory(GsonConverterFactory.create(gson))
                                                        .build();
        final DeckBrewService service = retrofit.create(DeckBrewService.class);
        final Call<List<Card>> cards = service.cards(request.getDeckBrewParams());
        cards.enqueue(callback);
    }

    /**
     * Hits DeckBrew Api
     */
    public static void card(@NonNull String id, @NonNull Callback<Card> callback) {
        Preconditions.checkArgument(id != null, "id");
        Preconditions.checkArgument(callback != null, "callback");
        final Gson gson = new CardGsonFactory().createCardGson();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.deckbrew.com/")
                                                        .addConverterFactory(GsonConverterFactory.create(gson))
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
        Preconditions.checkArgument(callback != null, "callback");
        final Gson gson = new CardGsonFactory().createCardGson();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.deckbrew.com/")
                                                        .addConverterFactory(GsonConverterFactory.create(gson))
                                                        .build();

        final DeckBrewService service = retrofit.create(DeckBrewService.class);
        final Call<List<Card>> typeaheads = service.typeahead(query);
        typeaheads.enqueue(callback);

    }

    /**
     * Hits DeckBrew Api
     */
    public static void sets(@NonNull Callback<List<String>> callback) {
        Preconditions.checkArgument(callback != null, "callback");
        final Gson gson = new CardGsonFactory().createCardGson();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.deckbrew.com/")
                                                        .addConverterFactory(GsonConverterFactory.create(gson))
                                                        .build();

        final DeckBrewService service = retrofit.create(DeckBrewService.class);
        final Call<List<String>> sets = service.sets();
        sets.enqueue(callback);
    }

    /**
     * Hits DeckBrew Api
     */
    public static void types(@NonNull Callback<List<String>> callback) {
        Preconditions.checkArgument(callback != null, "callback");
        final Gson gson = new CardGsonFactory().createCardGson();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.deckbrew.com/")
                                                        .addConverterFactory(GsonConverterFactory.create(gson))
                                                        .build();

        final DeckBrewService service = retrofit.create(DeckBrewService.class);
        final Call<List<String>> types = service.types();
        types.enqueue(callback);
    }

    /**
     * Hits DeckBrew Api
     */
    public static void supertypes(@NonNull Callback<List<String>> callback) {
        Preconditions.checkArgument(callback != null, "callback");
        final Gson gson = new CardGsonFactory().createCardGson();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.deckbrew.com/")
                                                        .addConverterFactory(GsonConverterFactory.create(gson))
                                                        .build();

        final DeckBrewService service = retrofit.create(DeckBrewService.class);
        final Call<List<String>> supertypes = service.supertypes();
        supertypes.enqueue(callback);
    }

    /**
     * Hits DeckBrew Api
     */
    public static void subtypes(@NonNull Callback<List<String>> callback) {
        Preconditions.checkArgument(callback != null, "callback");
        final Gson gson = new CardGsonFactory().createCardGson();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.deckbrew.com/")
                                                        .addConverterFactory(GsonConverterFactory.create(gson))
                                                        .build();

        final DeckBrewService service = retrofit.create(DeckBrewService.class);
        final Call<List<String>> subtypes = service.subtypes();
        subtypes.enqueue(callback);
    }

    /**
     * Hits DeckBrew Api
     */
    public static void colors(@NonNull Callback<List<String>> callback) {
        Preconditions.checkArgument(callback != null, "callback");
        final Gson gson = new CardGsonFactory().createCardGson();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.deckbrew.com/")
                                                        .addConverterFactory(GsonConverterFactory.create(gson))
                                                        .build();

        final DeckBrewService service = retrofit.create(DeckBrewService.class);
        final Call<List<String>> colors = service.colors();
        colors.enqueue(callback);
    }

    /**
     * Hits Gatherer Scraper TODO: HtmlScraperFactory
     */
    public static void simple(@NonNull GathererRequest request, @NonNull Callback<List<Card>> callback) {
        Preconditions.checkArgument(request != null, "request");
        Preconditions.checkArgument(callback != null, "callback");
        final Retrofit retrofit = new Retrofit.Builder().baseUrl("http://gatherer.wizards.com/").build();
        final GathererService service = retrofit.create(GathererService.class);
        final Call<List<Card>> cards = service.simple(request.getGathererParams());
        cards.enqueue(callback);
    }

    /**
     * Hits Gatherer Scraper TODO: HtmlScraperFactory
     */
    public static void advanced(@NonNull GathererRequest request, @NonNull Callback<List<Card>> callback) {
        Preconditions.checkArgument(request != null, "request");
        Preconditions.checkArgument(callback != null, "callback");
        final Retrofit retrofit = new Retrofit.Builder().baseUrl("http://gatherer.wizards.com/").build();
        final GathererService service = retrofit.create(GathererService.class);
        final Call<List<Card>> cards = service.advanced("advanced", request.getGathererParams());
        cards.enqueue(callback);
    }

    /**
     * Hits Gatherer Scraper TODO: HtmlScraperFactory
     */
    public static void detail(@NonNull String multiverseId, @NonNull Callback<Card> callback) {
        Preconditions.checkArgument(multiverseId != null, "multiverseId");
        Preconditions.checkArgument(callback != null, "callback");
        final Retrofit retrofit = new Retrofit.Builder().baseUrl("http://gatherer.wizards.com/").build();
        final GathererService service = retrofit.create(GathererService.class);
        final Call<Card> card = service.detail(multiverseId);
        card.enqueue(callback);
    }

    /**
     * Hits Gatherer Scraper TODO: HtmlScraperFactory
     */
    public static void random(@NonNull Callback<Card> callback) {
        Preconditions.checkArgument(callback != null, "callback");
        final Retrofit retrofit = new Retrofit.Builder().baseUrl("http://gatherer.wizards.com/").build();
        final GathererService service = retrofit.create(GathererService.class);
        final Call<Card> card = service.random("random");
        card.enqueue(callback);
    }
}
