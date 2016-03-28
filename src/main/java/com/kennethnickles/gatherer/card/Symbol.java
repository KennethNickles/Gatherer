package com.kennethnickles.gatherer.card;

import android.support.annotation.Nullable;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.kennethnickles.gatherer.util.EnumUtils;

import java.util.List;

/**
 * @author kenneth.nickles
 * @since 2015-07-18.
 */
public enum Symbol {

    BLACK("Black"),
    BLUE("Blue"),
    GREEN("Green"),
    RED("Red"),
    WHITE("White"),
    COLORLESS("Colorless"),
    VARIABLE_COLORLESS("Variable Colorless"),
    WHITE_OR_BLACK("White or Black"),
    TAP("Tap"),
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

    String mName;

    Symbol(String name) {
        this.mName = name;
    }

    public String getName() {
        return mName;
    }

    @Nullable
    public static Symbol from(String lookup) {
        if (Strings.isNullOrEmpty(lookup)) {
            return null;
        }
        for (Symbol symbol : values()) {
            if (EnumUtils.sanitize(symbol.getName()).equals(EnumUtils.sanitize(lookup))) {
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
