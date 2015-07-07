package com.kennethnickles.gatherer.card;

/**
 * @author kenneth.nickles
 * @since 2015-06-28.
 */
public enum Format {

    BLOCK,
    STANDARD,
    MODERN,
    COMMANDER,
    LEGACY,
    VINTAGE,
    BOOSTER_DRAFT,
    SEALED_DECK,
    TWO_HEADED_GIANT,
    TEAM_UNIFIED_CONSTRUCT,
    TEAM_LIMITED,
    TEAM_BOOSTER_DRAFT,
    TEAM_SEALED_DRAFT;

    public static Format from(String lookup) {
        for (Format format : values()) {
            if (format.name().equals(lookup)) {
                return format;
            }
        }
        return null;
    }
}
