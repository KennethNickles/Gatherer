package com.kennethnickles.gatherer.util;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author kenneth.nickles
 * @since 2016-03-29.
 */
public class Lists {

    private Lists() {
    }

    @NonNull
    public static <T> ArrayList<T> newArrayList() {
        return new ArrayList<>();
    }

    @NonNull
    public static <T> ArrayList<T> newArrayList(int capacity) {
        return new ArrayList<>(capacity);
    }

    @NonNull
    public static <T> ArrayList<T> newArrayList(T t1) {
        final ArrayList<T> arrayList = new ArrayList<>();
        arrayList.add(t1);
        return arrayList;
    }

    @NonNull
    public static <T> ArrayList<T> newArrayList(T t1, T t2) {
        final ArrayList<T> arrayList = new ArrayList<>();
        arrayList.add(t1);
        arrayList.add(t2);
        return arrayList;
    }

    @NonNull
    public static <T> ArrayList<T> newArrayList(T t1, T t2, T t3) {
        final ArrayList<T> arrayList = new ArrayList<>();
        arrayList.add(t1);
        arrayList.add(t2);
        arrayList.add(t3);
        return arrayList;
    }

    @NonNull
    public static <T> ArrayList<T> newArrayList(T... ts) {
        final ArrayList<T> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, ts);
        return arrayList;
    }

    @NonNull
    public static <T> List<T> of(T... ts) {
        final ArrayList<T> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, ts);
        return arrayList;
    }

    @NonNull
    public static <T> List<T> recycle(List<T> list) {
        list.clear();
        return list;
    }
}
