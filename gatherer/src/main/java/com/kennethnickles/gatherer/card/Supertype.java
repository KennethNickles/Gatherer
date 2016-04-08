package com.kennethnickles.gatherer.card;

import android.support.annotation.Nullable;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.kennethnickles.gatherer.util.EnumUtils;

import java.util.List;

/**
 * @author kenneth.nickles
 * @since 2016-04-04.
 */
public enum Supertype {

    basic,
    legendary,
    ongoing,
    snow,
    world;

    @Nullable
    public static Supertype from(String lookup) {
        if (Strings.isNullOrEmpty(lookup)) {
            return null;
        }
        for (Supertype supertype : values()) {
            if (EnumUtils.sanitize(supertype.name()).equals(EnumUtils.sanitize(lookup))) {
                return supertype;
            }
        }
        return null;
    }

    @Nullable
    public static List<Supertype> from(List<String> lookups) {
        if (lookups == null || lookups.isEmpty()) {
            return Lists.newArrayList();
        }
        final List<Supertype> types = Lists.newArrayList();
        for (String lookup : lookups) {
            types.add(from(lookup));
        }
        return types;
    }
}
