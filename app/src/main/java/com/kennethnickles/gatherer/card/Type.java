package com.kennethnickles.gatherer.card;

import android.support.annotation.Nullable;

/**
 * @author kenneth.nickles
 * @since 2015-06-28.
 */
public enum Type {

    ARTIFACT,
    BASIC,
    CONSPIRACY,
    CREATURE,
    ENCHANTMENT,
    INSTANT,
    LAND,
    LEGENDARY,
    ONGOING,
    PHENOMENON;

    @Nullable
    public static Type from(String lookup) {
        for (Type type : values()) {
            if (type.name().equals(lookup)) {
                return type;
            }
        }
        return null;
    }
}
