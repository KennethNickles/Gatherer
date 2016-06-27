package com.github.kennethnickles.gatherer.card;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.github.kennethnickles.gatherer.util.Lists;
import com.github.kennethnickles.gatherer.util.Maps;
import com.github.kennethnickles.gatherer.util.Preconditions;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.workday.postman.Postman;
import com.workday.postman.annotations.Parceled;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kenneth.nickles
 * @since 2015-06-28.
 */
@Parceled
public class Card implements Parcelable {

    public static final Creator<Card> CREATOR = Postman.getCreator(Card.class);

    protected Builder mState = new Builder();

    protected Card() {
        // Postman
    }

    protected Card(@NonNull Builder builder) {
        this.mState = Preconditions.checkNotNull(builder);
    }

    public String getName() {
        return mState.mName;
    }

    public String getId() {
        return mState.mId;
    }

    public String getUrl() {
        return mState.mUrl;
    }

    public String getStoreUrl() {
        return mState.mStoreUrl;
    }

    public Rules getRules() {
        return mState.mRules;
    }

    public List<Supertype> getSupertypes() {
        return mState.mSupertypes;
    }

    public List<Type> getTypes() {
        return mState.mTypes;
    }

    public List<Subtype> getSubtypes() {
        return mState.mSubtypes;
    }

    public Map<Format, Boolean> getFormats() {
        return mState.mFormats;
    }

    public Symbols getSymbols() {
        return mState.mSymbols;
    }

    public int getConvertedManaCost() {
        return mState.mConvertedManaCost;
    }

    public String getPower() {
        return mState.mPower;
    }

    public String getToughness() {
        return mState.mToughness;
    }

    public List<Edition> getEditions() {
        return mState.mEditions;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Postman.writeToParcel(this, dest);
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    @Parceled
    public static class Builder<C extends Card, B extends Builder<C, B>> implements Parcelable {

        public static final Creator<Builder> CREATOR = Postman.getCreator(Builder.class);

        String mName;
        String mId;
        String mUrl;
        String mStoreUrl;
        ArrayList<Supertype> mSupertypes = Lists.newArrayList();
        ArrayList<Type> mTypes = Lists.newArrayList();
        ArrayList<Subtype> mSubtypes = Lists.newArrayList();
        int mConvertedManaCost;
        Symbols mSymbols;
        Rules mRules;
        String mPower; // *
        String mToughness; // *
        HashMap<Format, Boolean> mFormats = Maps.newHashMap();
        ArrayList<Edition> mEditions = Lists.newArrayList();

        Builder() {
            // Postman
        }

        @NonNull
        public B withName(@NonNull String name) {
            this.mName = name;
            return getThis();
        }

        @NonNull
        public B withId(@NonNull String id) {
            this.mId = id;
            return getThis();
        }

        @NonNull
        public B withUrl(@NonNull String url) {
            this.mUrl = url;
            return getThis();
        }

        @NonNull
        public B withStoreUrl(@NonNull String storeUrl) {
            this.mStoreUrl = storeUrl;
            return getThis();
        }

        @NonNull
        public B withRule(@NonNull ArrayList<Symbol> symbols, int convertedManaCost, @NonNull String ruleText) {
            this.mRules.add(Rule.builder()
                                .withSymbols(symbols)
                                .withConvertedManaCost(convertedManaCost)
                                .withRuleText(ruleText)
                                .build());
            return getThis();
        }

        @NonNull
        public B withSupertypes(ArrayList<Supertype> supertypes) {
            this.mSupertypes = supertypes;
            return getThis();
        }

        @NonNull
        public B withSupertypes(JsonArray jsonArray) {
            if (jsonArray == null) {
                return getThis();
            }
            for (JsonElement jsonElement : jsonArray) {
                withSupertype(jsonElement.getAsString());
            }
            return getThis();
        }

        @NonNull
        public B withSupertype(String supertype) {
            this.mSupertypes.add(Supertype.from(supertype));
            return getThis();
        }

        @NonNull
        public B withSupertype(Supertype supertype) {
            this.mSupertypes.add(supertype);
            return getThis();
        }

        @NonNull
        public B withTypes(JsonArray jsonArray) {
            if (jsonArray == null) {
                return getThis();
            }
            for (JsonElement jsonElement : jsonArray) {
                withType(jsonElement.getAsString());
            }
            return getThis();
        }

        @NonNull
        public B withTypes(ArrayList<Type> types) {
            this.mTypes = types;
            return getThis();
        }

        @NonNull
        public B withType(String type) {
            this.mTypes.add(Type.from(type));
            return getThis();
        }

        @NonNull
        public B withType(Type type) {
            this.mTypes.add(type);
            return getThis();
        }

        @NonNull
        public B withSubtypes(ArrayList<Subtype> subtypes) {
            this.mSubtypes = subtypes;
            return getThis();
        }

        @NonNull
        public B withSubtypes(JsonArray jsonArray) {
            if (jsonArray == null) {
                return getThis();
            }
            for (JsonElement jsonElement : jsonArray) {
                withSubtype(jsonElement.getAsString());
            }
            return getThis();
        }

        @NonNull
        public B withSubtype(String type) {
            this.mSubtypes.add(Subtype.from(type));
            return getThis();
        }

        @NonNull
        public B withSubtype(Subtype subtype) {
            this.mSubtypes.add(subtype);
            return getThis();
        }

        @NonNull
        public B withFormat(HashMap<Format, Boolean> formats) {
            this.mFormats = formats;
            return getThis();
        }

        @NonNull
        public B withFormat(@Nullable JsonObject jsonObject) {
            if (jsonObject == null) {
                return getThis();
            }
            if (jsonObject.has("block")) {
                withFormat(Format.BLOCK, true);
            }
            if (jsonObject.has("standard")) {
                withFormat(Format.STANDARD, true);
            }
            if (jsonObject.has("modern")) {
                withFormat(Format.MODERN, true);
            }
            if (jsonObject.has("commander")) {
                withFormat(Format.COMMANDER, true);
            }
            if (jsonObject.has("legacy")) {
                withFormat(Format.LEGACY, true);
            }
            if (jsonObject.has("vintage")) {
                withFormat(Format.VINTAGE, true);
            }
            if (jsonObject.has("booster_draft")) {
                withFormat(Format.BOOSTER_DRAFT, true);
            }
            if (jsonObject.has("sealed_deck")) {
                withFormat(Format.SEALED_DECK, true);
            }
            if (jsonObject.has("two_headed_giant")) {
                withFormat(Format.TWO_HEADED_GIANT, true);
            }
            if (jsonObject.has("team_unified_construct")) {
                withFormat(Format.TEAM_UNIFIED_CONSTRUCT, true);
            }
            if (jsonObject.has("team_limited")) {
                withFormat(Format.TEAM_LIMITED, true);
            }
            if (jsonObject.has("team_booster_draft")) {
                withFormat(Format.TEAM_BOOSTER_DRAFT, true);
            }
            if (jsonObject.has("team_sealed_draft")) {
                withFormat(Format.TEAM_SEALED_DRAFT, true);
            }
            return getThis();
        }

        @NonNull
        public B withFormat(String format, boolean isValid) {
            this.mFormats.put(Format.from(format), isValid);
            return getThis();
        }

        @NonNull
        public B withFormat(Format format, boolean isValid) {
            this.mFormats.put(format, isValid);
            return getThis();
        }

        @NonNull
        public B withSymbols(ArrayList<Symbol> symbols) {
            this.mSymbols.addAll(symbols);
            return getThis();
        }

        @NonNull
        public B withSymbols(Symbols symbols) {
            Preconditions.checkArgument(symbols != null, "Symbols");
            this.mSymbols = symbols;
            return getThis();
        }

        @NonNull
        public B withSymbol(String symbol) {
            Preconditions.checkArgument(symbol != null, "Symbol");
            final Symbol mana = Symbol.from(symbol);
            this.mSymbols.add(mana);
            return getThis();
        }

        @NonNull
        public B withSymbol(Symbol symbol) {
            this.mSymbols.add(symbol);
            return getThis();
        }

        @NonNull
        public B withRules(ArrayList<Rule> rules) {
            Preconditions.checkArgument(rules != null, "Rules");
            this.mRules.addAll(rules);
            return getThis();
        }

        @NonNull
        public B withRules(@NonNull Rules rules) {
            Preconditions.checkArgument(rules != null, "Rules");
            this.mRules = rules;
            return getThis();
        }

        @NonNull
        public B withRule(@NonNull Rule rule) {
            Preconditions.checkArgument(rule != null, "Rule");
            this.mRules.add(rule);
            return getThis();
        }

        @NonNull
        public B withConvertedManaCost(int convertedManaCost) {
            this.mConvertedManaCost = convertedManaCost;
            return getThis();
        }

        @NonNull
        public B withConvertedManaCost(JsonPrimitive jsonPrimitive) {
            if (jsonPrimitive == null) {
                return getThis();
            }
            this.mConvertedManaCost = jsonPrimitive.getAsInt();
            return getThis();
        }

        @NonNull
        public B withPower(String power) {
            this.mPower = power;
            return getThis();
        }

        @NonNull
        public B withToughness(String toughness) {
            this.mToughness = toughness;
            return getThis();
        }

        @NonNull
        public B withEditions(@NonNull ArrayList<Edition> editions) {
            this.mEditions = editions;
            return getThis();
        }

        @NonNull
        public B withEdition(@NonNull Edition edition) {
            this.mEditions.add(edition);
            return getThis();
        }

        /**
         * Returns the correct type of builder.
         *
         * @return builder
         */
        @SuppressWarnings("unchecked")
        @NonNull
        private B getThis() {
            return (B) this;
        }

        public Card build() {
            return new Card(this);
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
