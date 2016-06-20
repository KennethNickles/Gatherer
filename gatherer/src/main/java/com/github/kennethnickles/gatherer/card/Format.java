package com.github.kennethnickles.gatherer.card;

import android.support.annotation.Nullable;

import com.github.kennethnickles.gatherer.util.Strings;
import com.github.kennethnickles.gatherer.util.Enums;

/**
 * @author kenneth.nickles
 * @since 2015-06-28.
 */
public enum Format {

    BLOCK(),
    STANDARD(),
    MODERN(),
    COMMANDER(),
    LEGACY(),
    VINTAGE(),
    BOOSTER_DRAFT("booster draft"),
    SEALED_DECK("sealed deck"),
    TWO_HEADED_GIANT("two-headed giant"),
    TEAM_UNIFIED_CONSTRUCT("team unified construct"),
    TEAM_LIMITED("team limited"),
    TEAM_BOOSTER_DRAFT("team booster draft"),
    TEAM_SEALED_DRAFT("team sealed draft");

    private final String mName;

    Format() {
        this.mName = name();
    }

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
            if (format.getName().equals(Enums.sanitize(lookup))) {
                return format;
            }
        }
        return null;
    }
}
