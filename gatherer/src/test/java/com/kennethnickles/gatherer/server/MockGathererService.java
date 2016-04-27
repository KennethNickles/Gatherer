package com.kennethnickles.gatherer.server;

import android.content.SharedPreferences;
import android.support.annotation.VisibleForTesting;

import com.kennethnickles.gatherer.card.Card;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;

/**
 * @author kenneth.nickles
 * @since 2016-04-09.
 */
public class MockGathererService implements GathererService {

    private final BehaviorDelegate<GathererService> delegate;
    private final SharedPreferences preferences;
    private final Map<GathererRequest, String> responses = new LinkedHashMap<>();

    @Inject
    MockGathererService(MockRetrofit mockRetrofit, SharedPreferences preferences) {
        this.delegate = mockRetrofit.create(GathererService.class);
        this.preferences = preferences;
    }

    @Override
    public Call<List<Card>> simple(@QueryMap Map<String, String> options) {
        return null;
    }

    @Override
    public Call<List<Card>> advanced(@Query("action") String action,
                                     @QueryMap Map<String, String> options) {
        return null;
    }

    @Override
    public Call<Card> detail(@Query("multiverseid") String id) {
        return null;
    }

    @Override
    public Call<Card> random(@Query("action") String action) {
        return null;
    }

    @VisibleForTesting
    public void addRequestResponsePair(GathererRequest request, String response) {
        responses.put(request, response);
    }

    @VisibleForTesting
    public void clearRequestResponsePairs() {
        responses.clear();
    }
}
