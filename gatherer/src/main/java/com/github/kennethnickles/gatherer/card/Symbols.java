package com.github.kennethnickles.gatherer.card;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import com.github.kennethnickles.gatherer.util.Lists;
import com.github.kennethnickles.gatherer.util.Preconditions;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.workday.postman.Postman;
import com.workday.postman.annotations.Parceled;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author kenneth.nickles
 * @since 2016-06-04.
 */
@Parceled
public class Symbols implements Parcelable {

    public static final Parcelable.Creator<Symbols> CREATOR = Postman.getCreator(Symbols.class);

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

    public static class Deserializer implements JsonDeserializer<Symbols> {

        @Override
        public Symbols deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            /**
             *     "text": "When you cast Kozilek, the Great Distortion, if you have fewer than
             *     seven cards in hand, draw cards equal to the difference.\nMenace\nDiscard a
             *     card with converted mana cost X: Counter target spell with converted mana cost
             *     X.",
             *
             *     "text": "Tap an untapped Cephalid you control: Tap target permanent
             *     .\n{U}{U}{U}: Tap all creatures without flying.",
             */
            final String regex = "\\{(.*?)\\}";
            final Pattern pattern = Pattern.compile(regex);
            final String colors = json.getAsString();
            final Matcher matcher = pattern.matcher(colors);
            final Builder builder = new Builder();
            while (matcher.find()) {
                builder.withSymbol(Symbol.from(colors.substring(matcher.start(), matcher.end())));
            }
            return new Symbols(builder);
        }
    }
}
