package com.github.kennethnickles.gatherer.util;

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

    public static boolean isNonEmpty(JsonElement jsonElement) {
        if (jsonElement == null) {
            return false;
        }
        if (jsonElement.getAsString() == null || jsonElement.getAsString().isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean isNonNegativeInt(JsonElement jsonElement) {
        if (jsonElement == null) {
            return false;
        }
        if (jsonElement.getAsString() != null && jsonElement.getAsString().isEmpty()) {
            // JsonPrimitives don't handle empty strings as numbers very well
            return false;
        }
        if (jsonElement.getAsNumber() == null || jsonElement.getAsNumber().intValue() < 0) {
            return false;
        }
        return true;
    }
}
