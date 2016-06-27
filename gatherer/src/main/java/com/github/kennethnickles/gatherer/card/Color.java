package com.github.kennethnickles.gatherer.card;

import android.support.annotation.Nullable;
import android.util.Log;
import com.github.kennethnickles.gatherer.util.Enums;

import java.util.HashSet;
import java.util.Set;

/**
 * @author kenneth.nickles
 * @since 2015-06-28.
 */
public enum Color {

    BLACK("BLACK", "B"),
    BLUE("BLUE", "U"),
    GREEN("GREEN", "G"),
    RED("RED", "R"),
    WHITE("WHITE", "W"),
    COLORLESS("COLORLESS", "C");

    private final String mName;
    private final String mSymbol;

    Color(String name, String symbol) {
        this.mName = name;
        this.mSymbol = symbol;
    }

    public String getSymbol() {
        return mSymbol;
    }

    public String getName() {
        return mName;
    }

    @Nullable
    public static Color from(String lookup) {
        for (Color color : values()) {
            if(color.getSymbol().equals(Enums.sanitize(lookup))) {
                return color;
            }
            if (color.getName().equals(Enums.sanitize(lookup))) {
                return color;
            }
        }
        Log.d("Color", "Missing Color: " + lookup);
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
