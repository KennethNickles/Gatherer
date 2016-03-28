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

    ARTIFACT("Artifact"),
    BASIC("Basic"),
    CONSPIRACY("Conspiracy"),
    CREATURE("Creature"),
    ENCHANTMENT("Enchantment"),
    INSTANT("Instant"),
    LAND("Land"),
    LEGENDARY("Legendary"),
    ONGOING("Ongoing"),
    PHENOMENON("Phenomenon"),
    PLANE("Plane"),
    PLANESWALKER("Planeswalker"),
    SCHEME("Scheme"),
    SNOW("Snow"),
    SORCERY("Sorcery"),
    TRIBAL("Tribal"),
    VANGUARD("Vanguard"),
    WORLD("World");

    String mName;

    Type(String name) {
        this.mName = name;
    }

    public String getName() {
        return mName;
    }

    @Nullable
    public static Type from(String lookup) {
        if (Strings.isNullOrEmpty(lookup)) {
            return null;
        }
        for (Type type : values()) {
            if (EnumUtils.sanitize(type.getName()).equals(EnumUtils.sanitize(lookup))) {
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
