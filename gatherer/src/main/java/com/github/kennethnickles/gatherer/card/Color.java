package com.github.kennethnickles.gatherer.card;

import java.util.HashSet;
import java.util.Set;

/**
 * @author kenneth.nickles
 * @since 2015-06-28.
 */
public enum Color {

    black("B"),
    blue("U"),
    green("G"),
    red("R"),
    white("W"),
    colorless("C");

    private final String mName;

    Color(String name) {
        this.mName = name;
    }

    public String getName() {
        return mName;
    }

    public static Color from(String lookup) {
        for (Color color : values()) {
            if (color.toString().equals(lookup)) {
                return color;
            }
        }
        return null;
    }

    public static Set<Color> from(Set<String> lookups) {
        final Set<Color> colors = new HashSet<>();
        for (String lookup : lookups) {
            final Color color = Color.from(lookup);
            if (color != null) {
                colors.add(color);
            }
        }
        return colors;
    }
}
