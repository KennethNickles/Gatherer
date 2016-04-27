package com.kennethnickles.gatherer.util;

import com.google.gson.JsonElement;

/**
 * @author kenneth.nickles
 * @since 2016-04-08.
 */
public class GsonUtils {

    private GsonUtils() {
    }

    public static boolean isNonNull(JsonElement jsonElement) {
        if (jsonElement == null) {
            return false;
        }
        if (jsonElement.isJsonNull()) {
            return false;
        }
        return true;
    }
}
