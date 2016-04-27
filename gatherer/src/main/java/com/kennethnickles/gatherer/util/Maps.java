package com.kennethnickles.gatherer.util;

import android.support.annotation.NonNull;

import java.util.HashMap;

/**
 * @author kenneth.nickles
 * @since 2016-03-29.
 */
public class Maps {

    private Maps() {
    }

    @NonNull
    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<>();
    }
}
