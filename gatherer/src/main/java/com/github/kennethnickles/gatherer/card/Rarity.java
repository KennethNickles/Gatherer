package com.github.kennethnickles.gatherer.card;

import android.support.annotation.Nullable;

import com.github.kennethnickles.gatherer.util.Lists;
import com.github.kennethnickles.gatherer.util.Strings;
import com.github.kennethnickles.gatherer.util.Enums;

import java.util.List;

/**
 * @author kenneth.nickles
 * @since 2015-06-28.
 */
public enum Rarity {

    common,
    uncommon,
    mythic,
    rare;

    @Nullable
    public static Rarity from(String lookup) {
        if (Strings.isNullOrEmpty(lookup)) {
            return null;
        }
        for (Rarity rarity : values()) {
            if (Enums.sanitize(rarity.name()).equals(Enums.sanitize(lookup))) {
                return rarity;
            }
        }
        return null;
    }

    @Nullable
    public static List<Rarity> from(List<String> lookups) {
        if (lookups == null || lookups.isEmpty()) {
            return Lists.newArrayList();
        }
        final List<Rarity> types = Lists.newArrayList();
        for (String lookup : lookups) {
            types.add(from(lookup));
        }
        return types;
    }
}
