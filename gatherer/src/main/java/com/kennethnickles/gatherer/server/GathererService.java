package com.kennethnickles.gatherer.server;

import com.kennethnickles.gatherer.card.Card;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @author kenneth.nickles
 * @since 2016-04-04.
 */
public interface GathererService {

    @GET("/Pages/Search/Default.aspx")
    Call<List<Card>> simple(@QueryMap Map<String, String> options);

    @GET("/Pages/Search/Default.aspx")
    Call<List<Card>> advanced(@Query("action") String action,
                              @QueryMap Map<String, String> options);

    @GET("/Pages/Card/Details.aspx")
    Call<Card> detail(@Query("multiverseid") String id);

    @GET("/Pages/Card/Details.aspx")
    Call<Card> random(@Query("action") String action);
}
