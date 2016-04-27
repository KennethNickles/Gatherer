package com.kennethnickles.gatherer.card;

import android.support.annotation.Nullable;

import com.kennethnickles.gatherer.util.Enums;
import com.kennethnickles.gatherer.util.Strings;

/**
 * @author kenneth.nickles
 * @since 2015-06-28.
 */
public enum Format {

    block(),
    standard(),
    modern(),
    commander(),
    legacy(),
    vintage(),
    booster_draft("booster draft"),
    sealed_deck("sealed deck"),
    two_headed_giant("two-headed giant"),
    team_unified_construct("team unified construct"),
    team_limited("team limited"),
    team_booster_draft("team booster draft"),
    team_sealed_draft("team sealed draft");

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
            if (Enums.sanitize(format.name()).equals(Enums.sanitize(lookup))) {
                return format;
            }
        }
        return null;
    }
}
