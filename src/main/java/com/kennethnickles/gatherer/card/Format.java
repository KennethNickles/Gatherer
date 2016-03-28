package com.kennethnickles.gatherer.card;

import android.support.annotation.Nullable;
import com.google.common.base.Strings;
import com.kennethnickles.gatherer.util.EnumUtils;

/**
 * @author kenneth.nickles
 * @since 2015-06-28.
 */
public enum Format {

    // TODO: Remove name
    BLOCK("Block"),
    STANDARD("Standard"),
    MODERN("Modern"),
    COMMANDER("Commander"),
    LEGACY("Legacy"),
    VINTAGE("Vintage"),
    BOOSTER_DRAFT("Booster Draft"),
    SEALED_DECK("Sealed Deck"),
    TWO_HEADED_GIANT("Two-Headed Giant"),
    TEAM_UNIFIED_CONSTRUCT("Team Unified Construct"),
    TEAM_LIMITED("Team Limited"),
    TEAM_BOOSTER_DRAFT("Team Booster Draft"),
    TEAM_SEALED_DRAFT("Team Sealed Draft");

    private final String mName;

    Format(String name) {
        this.mName = name;
    }

    public String getName() {
        return mName;
    }

    @Nullable
    public static Format from(String lookup) {
        if (Strings.isNullOrEmpty(lookup)) {
            return null;
        }
        for (Format format : values()) {
            if (EnumUtils.sanitize(format.name()).equals(EnumUtils.sanitize(lookup))) {
                return format;
            }
        }
        return null;
    }
}
