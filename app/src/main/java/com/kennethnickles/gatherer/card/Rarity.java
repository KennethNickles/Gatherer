package com.kennethnickles.gatherer.card;

/**
 * @author kenneth.nickles
 * @since 2015-06-28.
 */
public enum Rarity {

    MYTHIC,
    RARE,
    UNCOMMON,
    COMMON;

    public static Rarity from(String lookup) {
        for (Rarity rarity : values()) {
            if (rarity.name().equals(lookup)) {
                return rarity;
            }
        }
        return null;
    }
}
