package com.kennethnickles.gatherer.card;

import android.support.annotation.Nullable;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.kennethnickles.gatherer.util.Enums;
import com.kennethnickles.gatherer.util.Lists;
import com.kennethnickles.gatherer.util.Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    white_or_back("White or Black"),
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

    public static class CostSymbolDeserializer implements JsonDeserializer<ArrayList<Symbol>> {

        @Override
        public ArrayList<Symbol> deserialize(JsonElement json,
                                             java.lang.reflect.Type typeOfT,
                                             JsonDeserializationContext context)
                throws JsonParseException {
            final ArrayList<Symbol> symbols = Lists.newArrayList();
            final String text = json.getAsJsonObject().get("cost").getAsString();
            final String regex = "\\{(.*?)\\}";
            final Pattern pattern = Pattern.compile(regex);
            final Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                symbols.add(Symbol.from(text.substring(matcher.start(), matcher.end())));
            }
            return symbols;
        }
    }

    public static class RuleSymbolDeserializer implements JsonDeserializer<ArrayList<Symbol>> {

        final int mLineNumber;

        public RuleSymbolDeserializer(int lineNumber) {
            this.mLineNumber = lineNumber;
        }

        @Override
        public ArrayList<Symbol> deserialize(JsonElement json,
                                             java.lang.reflect.Type typeOfT,
                                             JsonDeserializationContext context)
                throws JsonParseException {
            final ArrayList<Symbol> symbols = Lists.newArrayList();
            final String text = json.getAsJsonObject().get("text").getAsString();
            final String regex = "\\{(.*?)\\}";
            final Pattern pattern = Pattern.compile(regex);
            final String line = text.split("\n")[mLineNumber];
            final Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                symbols.add(Symbol.from(line.substring(matcher.start(), matcher.end())));
            }
            return symbols;
        }
    }
}
