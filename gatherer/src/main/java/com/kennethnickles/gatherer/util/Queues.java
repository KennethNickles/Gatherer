package com.kennethnickles.gatherer.util;

import java.util.ArrayDeque;

/**
 * @author kenneth.nickles
 * @since 2016-03-29.
 */
public class Queues {

    private Queues() {
    }

    public static <E> ArrayDeque<E> newArrayDeque() {
        return new ArrayDeque<E>();
    }
}
