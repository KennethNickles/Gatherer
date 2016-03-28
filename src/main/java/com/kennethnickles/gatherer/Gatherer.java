package com.kennethnickles.gatherer;

import android.support.annotation.NonNull;
import com.google.common.collect.Lists;
import com.kennethnickles.gatherer.card.Card;
import com.kennethnickles.gatherer.server.GathererRequestClient;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author kenneth.nickles
 * @since 2015-06-27.
 */
public class Gatherer {

    public static ArrayList<Card> request(@NonNull GathererRequestClient.Builder builder) {
        try {
            return new GathererRequestClient().request(builder);
        } catch (IOException e) {
            return Lists.newArrayList();
        }
    }
}
