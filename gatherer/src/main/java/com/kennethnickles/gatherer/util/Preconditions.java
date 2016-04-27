package com.kennethnickles.gatherer.util;

import android.support.annotation.NonNull;

/**
 * @author kenneth.nickles
 * @since 2016-03-29.
 */
public class Preconditions {

    public static void checkState(boolean state) {
        if (!state) {
            throw new IllegalStateException("");
        }
    }

    public static void checkState(boolean state, @NonNull String message) {
        if (!state) {
            throw new IllegalStateException(message);
        }
    }

    public static void checkArgument(boolean argument) {
        if (!argument) {
            throw new IllegalArgumentException("");
        }
    }

    public static void checkArgument(boolean argument, @NonNull String message) {
        if (!argument) {
            throw new IllegalArgumentException(message);
        }
    }


    public static <T> T checkNotNull(T t) {
        if (t == null) {
            throw new NullPointerException("");
        }
        return t;
    }

    public static <T> T checkNotNull(T t, @NonNull String message) {
        if (t == null) {
            throw new NullPointerException(message);
        }
        return t;
    }
}
