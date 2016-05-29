package com.github.kennethnickles.gatherer.card;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import com.github.kennethnickles.gatherer.util.Lists;

import com.workday.postman.Postman;
import com.workday.postman.annotations.Parceled;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kenneth.nickles
 * @since 2016-03-07.
 */
@Parceled
public class Rule implements Parcelable {

    public static final Creator<Rule> CREATOR = Postman.getCreator(Rule.class);

    Builder mState;

    Rule() {
        // Postman
    }

    private Rule(Builder builder) {
        this.mState = builder;
    }

    @NonNull
    public List<Symbol> getSymbols() {
        return mState.mSymbols;
    }

    public int getConvertedManaCost() {
        return mState.mConvertedManaCost;
    }

    @NonNull
    public String getRuleText() {
        return mState.mRuleText;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Postman.writeToParcel(this, dest);
    }

    @Parceled
    public static class Builder implements Parcelable {

        public static final Creator<Builder> CREATOR = Postman.getCreator(Builder.class);

        ArrayList<Symbol> mSymbols = Lists.newArrayList();
        int mConvertedManaCost;
        String mRuleText;

        Builder() {
            // Postman
        }

        public Builder withSymbol(@NonNull String symbol) {
            this.mSymbols.add(Symbol.from(symbol));
            return this;
        }

        public Builder withSymbols(@NonNull ArrayList<Symbol> symbols) {
            this.mSymbols = symbols;
            return this;
        }

        public Builder withConvertedManaCost(int convertedManaCost) {
            this.mConvertedManaCost = convertedManaCost;
            return this;
        }

        public Builder withRuleText(@NonNull String ruleText) {
            this.mRuleText = ruleText;
            return this;
        }

        public Rule build() {
            return new Rule(this);
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


    public static class Deserializer implements JsonDeserializer<ArrayList<Rule>> {

        @Override
        public ArrayList<Rule> deserialize(JsonElement json,
                                           java.lang.reflect.Type typeOfT,
                                           JsonDeserializationContext context)
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
            final String text = json.getAsJsonObject().get("text").getAsString();
            final ArrayList<Rule> rules = Lists.newArrayList();
            final String[] lines = text.split("\n");
            for (int i = 0; i < lines.length; i++) {
                final String line = lines[i];
                final Builder builder = new Builder();
                builder.withSymbols(new Symbol.RuleSymbolDeserializer(i).deserialize(json,
                                                                                     typeOfT,
                                                                                     context));
                builder.withConvertedManaCost(builder.mSymbols.size());
                builder.withRuleText(line);
                rules.add(builder.build());
            }
            return rules;
        }
    }
}
