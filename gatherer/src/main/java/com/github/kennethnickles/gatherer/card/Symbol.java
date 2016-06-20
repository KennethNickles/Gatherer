package com.github.kennethnickles.gatherer.card;

import android.support.annotation.Nullable;
import android.util.Log;
import com.github.kennethnickles.gatherer.util.Enums;
import com.github.kennethnickles.gatherer.util.Lists;
import com.github.kennethnickles.gatherer.util.Strings;

import java.util.List;

/**
 * @author kenneth.nickles
 * @since 2015-07-18.
 */
public enum Symbol {

    BLACK("BLACK", "B"),
    BLUE("BLUE", "U"),
    GREEN("GREEN", "G"),
    RED("RED", "R"),
    WHITE("WHITE", "W"),
    COLORLESS("COLORLESS", "C"),
    VARIABLE_COLORLESS("VARIABLE COLORLESS", "X"),
    BLACK_OR_RED("BLACK OR RED", "B/R"),
    BLACK_OR_GREEN("BLACK OR GREEN", "B/G"),
    BLUE_OR_BLACK("BLUE OR BLACK", "U/B"),
    BLUE_OR_RED("BLUE OR RED", "U/R"),
    GREEN_OR_WHITE("GREEN OR WHITE", "G/W"),
    GREEN_OR_BLUE("GREEN OR BLUE", "G/U"),
    RED_OR_GREEN("RED OR GREEN", "R/G"),
    RED_OR_WHITE("RED OR WHITE", "R/W"),
    WHITE_OR_BLUE("WHITE OR BLUE", "W/U"),
    WHITE_OR_BLACK("WHITE OR BLACK", "W/B"),
    TWO_OR_BLACK("TWO OR BLACK", "2/B"),
    TWO_OR_BLUE("TWO OR BLUE", "2/U"),
    TWO_OR_GREEN("TWO OR GREEN", "2/G"),
    TWO_OR_RED("TWO OR RED", "2/R"),
    TWO_OR_WHITE("TWO OR WHITE", "2/W"),
    TAP("TAP"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    ELEVEN("11"),
    TWELVE("12"),
    THIRTEEN("13"),
    FOURTEEN("14"),
    FIFTEEN("15"),
    SIXTEEN("16"),
    SEVENTEEN("17"),
    EIGHTEEN("18"),
    NINETEEN("19"),
    TWENTY("20");

    String mSymbol;
    String mName;

    Symbol(String symbol) {
        this.mSymbol = symbol;
        this.mName = name();
    }

    Symbol(String symbol, String name) {
        this.mSymbol = symbol;
        this.mName = name;
    }

    public String getSymbol() {
        return mSymbol;
    }

    public String getName() {
        return mName;
    }

    public int getCmc() {
        switch (this) {
            case BLACK:
            case BLUE:
            case GREEN:
            case RED:
            case WHITE:
            case COLORLESS:
            case BLACK_OR_GREEN:
            case BLACK_OR_RED:
            case BLUE_OR_BLACK:
            case BLUE_OR_RED:
            case GREEN_OR_BLUE:
            case GREEN_OR_WHITE:
            case RED_OR_GREEN:
            case RED_OR_WHITE:
            case WHITE_OR_BLUE:
            case WHITE_OR_BLACK:
                return 1;
            case VARIABLE_COLORLESS:
                return 0;
            case TAP:
                return 0;
            case TWO_OR_BLACK:
            case TWO_OR_BLUE:
            case TWO_OR_GREEN:
            case TWO_OR_RED:
            case TWO_OR_WHITE:
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
        final String sanitized = Enums.sanitize(lookup);
        if (sanitized == null || sanitized.isEmpty()) {
            return null;
        }
        // TODO: Add Name support for lookups
        final Symbol symbol = Symbols.SYMBOL_MAP.get(sanitized);
        if (symbol == null) {
            Log.d("Symbol", "Missing Symbol: " + lookup);
            return null;
        }
        return symbol;
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
