package com.github.kennethnickles.gatherer.card;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import com.github.kennethnickles.gatherer.util.Enums;
import com.github.kennethnickles.gatherer.util.Lists;
import com.github.kennethnickles.gatherer.util.Preconditions;
import com.workday.postman.Postman;
import com.workday.postman.annotations.Parceled;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kenneth.nickles
 * @since 2016-06-04.
 */
@Parceled
public class Symbols implements Parcelable {

    public static final Parcelable.Creator<Symbols> CREATOR = Postman.getCreator(Symbols.class);

    public static final Map<String, Symbol> SYMBOL_MAP = new HashMap<>(Symbol.values().length);

    static {
        SYMBOL_MAP.put("B", Symbol.BLACK);
        SYMBOL_MAP.put("U", Symbol.BLUE);
        SYMBOL_MAP.put("G", Symbol.GREEN);
        SYMBOL_MAP.put("R", Symbol.RED);
        SYMBOL_MAP.put("W", Symbol.WHITE);
        SYMBOL_MAP.put("C", Symbol.COLORLESS);
        SYMBOL_MAP.put("X", Symbol.VARIABLE_COLORLESS);
        SYMBOL_MAP.put("B/R", Symbol.BLACK_OR_RED);
        SYMBOL_MAP.put("B/G", Symbol.BLACK_OR_GREEN);
        SYMBOL_MAP.put("U/B", Symbol.BLUE_OR_BLACK);
        SYMBOL_MAP.put("U/R", Symbol.BLUE_OR_RED);
        SYMBOL_MAP.put("G/W", Symbol.GREEN_OR_WHITE);
        SYMBOL_MAP.put("G/U", Symbol.GREEN_OR_BLUE);
        SYMBOL_MAP.put("R/G", Symbol.RED_OR_GREEN);
        SYMBOL_MAP.put("R/W", Symbol.RED_OR_WHITE);
        SYMBOL_MAP.put("W/U", Symbol.WHITE_OR_BLUE);
        SYMBOL_MAP.put("W/B", Symbol.WHITE_OR_BLACK);
        SYMBOL_MAP.put("2/B", Symbol.TWO_OR_BLACK);
        SYMBOL_MAP.put("2/U", Symbol.TWO_OR_BLUE);
        SYMBOL_MAP.put("2/G", Symbol.TWO_OR_GREEN);
        SYMBOL_MAP.put("2/R", Symbol.TWO_OR_RED);
        SYMBOL_MAP.put("2/W", Symbol.TWO_OR_WHITE);
        SYMBOL_MAP.put("T", Symbol.TAP);
        SYMBOL_MAP.put("0", Symbol.ZERO);
        SYMBOL_MAP.put("1", Symbol.ONE);
        SYMBOL_MAP.put("2", Symbol.TWO);
        SYMBOL_MAP.put("3", Symbol.THREE);
        SYMBOL_MAP.put("4", Symbol.FOUR);
        SYMBOL_MAP.put("5", Symbol.FIVE);
        SYMBOL_MAP.put("6", Symbol.SIX);
        SYMBOL_MAP.put("7", Symbol.SEVEN);
        SYMBOL_MAP.put("8", Symbol.EIGHT);
        SYMBOL_MAP.put("9", Symbol.NINE);
        SYMBOL_MAP.put("10", Symbol.TEN);
        SYMBOL_MAP.put("11", Symbol.ELEVEN);
        SYMBOL_MAP.put("12", Symbol.TWELVE);
        SYMBOL_MAP.put("13", Symbol.THIRTEEN);
        SYMBOL_MAP.put("14", Symbol.FOURTEEN);
        SYMBOL_MAP.put("15", Symbol.FIFTEEN);
        SYMBOL_MAP.put("16", Symbol.SIXTEEN);
        SYMBOL_MAP.put("17", Symbol.SEVENTEEN);
        SYMBOL_MAP.put("18", Symbol.EIGHTEEN);
        SYMBOL_MAP.put("19", Symbol.NINETEEN);
        SYMBOL_MAP.put("20", Symbol.TWENTY);
        SYMBOL_MAP.put("S", Symbol.SNOW);
        SYMBOL_MAP.put("B/P", Symbol.PHYREXIAN_BLACK);
        SYMBOL_MAP.put("U/P", Symbol.PHYREXIAN_BLUE);
        SYMBOL_MAP.put("G/P", Symbol.PHYREXIAN_GREEN);
        SYMBOL_MAP.put("R/P", Symbol.PHYREXIAN_RED);
        SYMBOL_MAP.put("W/P", Symbol.PHYREXIAN_WHITE);

        Enums.assertFullMappingValues(Symbol.class, SYMBOL_MAP);
    }

    Builder mState;

    Symbols() {
        // Postman
    }

    private Symbols(Builder builder) {
        this.mState = builder;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Postman.writeToParcel(this, dest);
    }

    public void add(@NonNull Symbol symbol) {
        Preconditions.checkArgument(symbol != null, "Symbol");
        this.mState.mSymbols.add(symbol);
    }

    public void addAll(@NonNull List<Symbol> symbols) {
        Preconditions.checkArgument(symbols != null, "Symbols");
        this.mState.mSymbols.addAll(symbols);
    }

    public int size() {
        return this.mState.mSymbols.size();
    }

    public Symbol get(int index) {
        return this.mState.mSymbols.get(index);
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    @Parceled
    public static class Builder implements Parcelable {

        public static final Creator<Builder> CREATOR = Postman.getCreator(Builder.class);

        ArrayList<Symbol> mSymbols = Lists.newArrayList();

        Builder() {
            // Postman
        }

        @NonNull
        public Builder withSymbol(@NonNull Symbol symbol) {
            Preconditions.checkArgument(symbol != null, "Symbol");
            this.mSymbols.add(symbol);
            return this;
        }

        @NonNull
        public Builder withSymbols(@NonNull ArrayList<Symbol> symbols) {
            Preconditions.checkArgument(symbols != null, "Symbols");
            this.mSymbols = symbols;
            return this;
        }

        public Symbols build() {
            return new Symbols(this);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            Postman.writeToParcel(this, dest);
        }
    }
}
