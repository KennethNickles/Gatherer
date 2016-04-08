package com.kennethnickles.gatherer.card;

import android.support.annotation.Nullable;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.kennethnickles.gatherer.util.EnumUtils;

import java.util.List;

/**
 * @author kenneth.nickles
 * @since 2015-06-28.
 */
public enum Type {

    artifact,
    conspiracy,
    creature,
    enchantment,
    instant,
    land,
    phenomenon,
    plane,
    planeswalker,
    scheme,
    sorcery,
    tribal,
    vanguard;

    @Nullable
    public static Type from(String lookup) {
        if (Strings.isNullOrEmpty(lookup)) {
            return null;
        }
        for (Type type : values()) {
            if (EnumUtils.sanitize(type.name()).equals(EnumUtils.sanitize(lookup))) {
                return type;
            }
        }
        return null;
    }

    @Nullable
    public static List<Type> from(List<String> lookups) {
        if (lookups == null || lookups.isEmpty()) {
            return Lists.newArrayList();
        }
        final List<Type> types = Lists.newArrayList();
        for (String lookup : lookups) {
            types.add(from(lookup));
        }
        return types;
    }
}
