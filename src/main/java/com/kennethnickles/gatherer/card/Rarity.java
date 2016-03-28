package com.kennethnickles.gatherer.card;

import android.support.annotation.Nullable;
import com.google.common.base.Strings;
import com.kennethnickles.gatherer.util.EnumUtils;

/**
 * @author kenneth.nickles
 * @since 2015-06-28.
 */
public enum Rarity {

    MYTHIC,
    RARE,
    UNCOMMON,
    COMMON;

    @Nullable
    public static Rarity from(String lookup) {
        if(Strings.isNullOrEmpty(lookup)) {
            return null;
        }
        for (Rarity rarity : values()) {
            if (EnumUtils.sanitize(rarity.name()).equals(EnumUtils.sanitize(lookup))) {
                return rarity;
            }
        }
        return null;
    }
}
