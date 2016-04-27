package com.kennethnickles.gatherer.util;

import android.support.annotation.NonNull;

import java.util.HashSet;

/**
 * @author kenneth.nickles
 * @since 2016-03-29.
 */
public class Sets {

    private Sets() {
    }

    public static <T> HashSet<T> newHashSet() {
        return new HashSet<>();
    }

    public static <T> HashSet<T> newHashSet(@NonNull T t1) {
        final HashSet<T> hashSet = new HashSet<>();
        hashSet.add(t1);
        return hashSet;
    }

    public static <T> HashSet<T> newHashSet(@NonNull T t1, @NonNull T t2) {
        final HashSet<T> hashSet = new HashSet<>();
        hashSet.add(t1);
        hashSet.add(t2);
        return hashSet;
    }

    public static <T> HashSet<T> newHashSet(@NonNull T t1, @NonNull T t2, @NonNull T t3) {
        final HashSet<T> hashSet = new HashSet<>();
        hashSet.add(t1);
        hashSet.add(t2);
        hashSet.add(t3);
        return hashSet;
    }
}
