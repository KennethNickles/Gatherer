package com.github.kennethnickles.gatherer.card;

import android.support.annotation.Nullable;
import com.github.kennethnickles.gatherer.util.Enums;
import com.github.kennethnickles.gatherer.util.Lists;
import com.github.kennethnickles.gatherer.util.Strings;
import java.util.List;

/**
 * @author kenneth.nickles
 * @since 2015-07-18.
 */
public enum Symbol {

    black("B"),
    blue("U"),
    green("G"),
    red("R"),
    white("White", "W"),
    colorless("Colorless", "C"),
    variable_colorless("Variable Colorless", "X"),
    black_or_red("Black or Red", "B/R"),
    black_or_green("Black or Green", "B/G"),
    blue_or_black("Blue or Black", "U/B"),
    blue_or_red("Blue or Red", "U/R"),
    green_or_white("Green or White", "G/W"),
    green_or_blue("Green or Blue", "G/U"),
    red_or_green("Red or Green", "R/G"),
    red_or_white("Red or White", "R/W"),
    white_or_blue("White or Blue", "W/U"),
    white_or_black("White or Black", "W/B"),
    two_or_black("Two or Black", "2/B"),
    two_or_blue("Two or Blue", "2/U"),
    two_or_green("Two or Green", "2/G"),
    two_or_red("Two or Red", "2/R"),
    two_or_white("Two or White", "2/W"),
    tap("Tap"),
    one("1"),
    two("2"),
    three("3"),
    four("4"),
    five("5"),
    six("6"),
    seven("7"),
    eight("8"),
    nine("9"),
    ten("10"),
    eleven("11"),
    twelve("12"),
    thirteen("13"),
    fourteen("14"),
    fifteen("15"),
    sixteen("16"),
    seventeen("17"),
    eighteen("18"),
    nineteen("19"),
    twenty("20");

    String mName;
    String mSymbol;

    Symbol(String symbol) {
        this.mName = symbol;
        this.mSymbol = symbol;
    }

    Symbol(String name, String symbol) {
        this.mName = name;
        this.mSymbol = symbol;
    }

    public String getName() {
        return mName;
    }

    public String getSymbol() {
        return mSymbol;
    }

    public int getCmc() {
        switch (this) {
            case black:
            case blue:
            case green:
            case red:
            case white:
            case colorless:
            case black_or_green:
            case black_or_red:
            case blue_or_black:
            case blue_or_red:
            case green_or_blue:
            case green_or_white:
            case red_or_green:
            case red_or_white:
            case white_or_blue:
            case white_or_black:
                return 1;
            case variable_colorless:
                return 0;
            case tap:
                return 0;
            case two_or_black:
            case two_or_blue:
            case two_or_green:
            case two_or_red:
            case two_or_white:
                return 2;
            default:
                return Integer.valueOf(mSymbol);

        }
    }

    @Nullable
    public static Symbol from(String lookup) {
        if (Strings.isNullOrEmpty(lookup)) {
            return null;
        }
        for (Symbol symbol : values()) {
            if (Enums.sanitize(symbol.getName()).equals(Enums.sanitize(lookup))) {
                return symbol;
            }
            if (Enums.sanitize(symbol.getSymbol()).equals(Enums.sanitize(lookup))) {
                return symbol;
            }
        }
        return null;
    }

    @Nullable
    public static List<Symbol> from(List<String> lookups) {
        if (lookups == null || lookups.isEmpty()) {
            return Lists.newArrayList();
        }
        final List<Symbol> symbols = Lists.newArrayList();
        for (String lookup : lookups) {
            symbols.add(from(lookup));
        }
        return symbols;
    }
}
