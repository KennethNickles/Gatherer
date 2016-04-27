package com.kennethnickles.gatherer.card;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.kennethnickles.gatherer.util.GsonUtils;
import com.kennethnickles.gatherer.util.Lists;
import com.kennethnickles.gatherer.util.Maps;
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

    protected Builder mState;

    protected Card() {
        // Postman
    }

    protected Card(Builder builder) {
        this.mState = builder;
    }

    public void setState(Builder state) {
        this.mState = state;
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

    public List<Rule> getRules() {
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

    public List<Symbol> getSymbols() {
        return mState.mSymbols;
    }

    public int getConvertedManaCost() {
        return mState.mConvertedManaCost;
    }

    public int getPower() {
        return mState.mPower;
    }

    public int getToughness() {
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
        ArrayList<Symbol> mSymbols = Lists.newArrayList();
        ArrayList<Rule> mRules = Lists.newArrayList();
        int mPower;
        int mToughness;
        HashMap<Format, Boolean> mFormats = Maps.newHashMap();
        ArrayList<Edition> mEditions = Lists.newArrayList();

        protected Builder() {
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

        public B withRule(@NonNull ArrayList<Symbol> symbols,
                          int convertedManaCost,
                          @NonNull String ruleText) {
            this.mRules.add(Rule.builder()
                                .withSymbols(symbols)
                                .withConvertedManaCost(convertedManaCost)
                                .withRuleText(ruleText)
                                .build());
            return getThis();
        }

        public B withSupertypes(ArrayList<Supertype> supertypes) {
            this.mSupertypes = supertypes;
            return getThis();
        }

        public B withSupertypes(JsonArray jsonArray) {
            if (jsonArray == null) {
                return getThis();
            }
            for (JsonElement jsonElement : jsonArray) {
                withSupertype(jsonElement.getAsString());
            }
            return getThis();
        }

        public B withSupertype(String supertype) {
            this.mSupertypes.add(Supertype.from(supertype));
            return getThis();
        }

        public B withSupertype(Supertype supertype) {
            this.mSupertypes.add(supertype);
            return getThis();
        }

        public B withTypes(JsonArray jsonArray) {
            if (jsonArray == null) {
                return getThis();
            }
            for (JsonElement jsonElement : jsonArray) {
                withType(jsonElement.getAsString());
            }
            return getThis();
        }

        public B withTypes(ArrayList<Type> types) {
            this.mTypes = types;
            return getThis();
        }

        public B withType(String type) {
            this.mTypes.add(Type.from(type));
            return getThis();
        }

        public B withType(Type type) {
            this.mTypes.add(type);
            return getThis();
        }

        public B withSubtypes(ArrayList<Subtype> subtypes) {
            this.mSubtypes = subtypes;
            return getThis();
        }

        public B withSubtypes(JsonArray jsonArray) {
            if (jsonArray == null) {
                return getThis();
            }
            for (JsonElement jsonElement : jsonArray) {
                withSubtype(jsonElement.getAsString());
            }
            return getThis();
        }

        public B withSubtype(String type) {
            this.mSubtypes.add(Subtype.from(type));
            return getThis();
        }

        public B withSubtype(Subtype subtype) {
            this.mSubtypes.add(subtype);
            return getThis();
        }

        public B withFormats(HashMap<Format, Boolean> formats) {
            this.mFormats = formats;
            return getThis();
        }

        public B withFormats(JsonObject jsonObject) {
            if (jsonObject == null) {
                return getThis();
            }
            if (jsonObject.has("block")) {
                withFormat(Format.block, true);
            }
            if (jsonObject.has("standard")) {
                withFormat(Format.standard, true);
            }
            if (jsonObject.has("modern")) {
                withFormat(Format.modern, true);
            }
            if (jsonObject.has("commander")) {
                withFormat(Format.commander, true);
            }
            if (jsonObject.has("legacy")) {
                withFormat(Format.legacy, true);
            }
            if (jsonObject.has("booster_draft")) {
                withFormat(Format.booster_draft, true);
            }
            if (jsonObject.has("sealed_deck")) {
                withFormat(Format.sealed_deck, true);
            }
            if (jsonObject.has("two_headed_giant")) {
                withFormat(Format.two_headed_giant, true);
            }
            if (jsonObject.has("team_unified_construct")) {
                withFormat(Format.team_unified_construct, true);
            }
            if (jsonObject.has("team_limited")) {
                withFormat(Format.team_limited, true);
            }
            if (jsonObject.has("team_booster_draft")) {
                withFormat(Format.team_booster_draft, true);
            }
            if (jsonObject.has("team_sealed_draft")) {
                withFormat(Format.team_sealed_draft, true);
            }
            return getThis();
        }

        public B withFormat(String format, boolean isValid) {
            this.mFormats.put(Format.from(format), isValid);
            return getThis();
        }

        public B withFormat(Format format, boolean isValid) {
            this.mFormats.put(format, isValid);
            return getThis();
        }

        public B withSymbols(ArrayList<Symbol> symbols) {
            this.mSymbols = symbols;
            return getThis();
        }

        public B withSymbols(String symbol) {
            this.mSymbols.add(Symbol.from(symbol));
            return getThis();
        }

        public B withSymbol(Symbol symbol) {
            this.mSymbols.add(symbol);
            return getThis();
        }

        public B withRules(ArrayList<Rule> rules) {
            this.mRules = rules;
            return getThis();
        }

        public B withRule(@NonNull Rule rule) {
            this.mRules.add(rule);
            return getThis();
        }

        public B withConvertedManaCost(int convertedManaCost) {
            this.mConvertedManaCost = convertedManaCost;
            return getThis();
        }

        public B withConvertedManaCost(JsonPrimitive jsonPrimitive) {
            if (jsonPrimitive == null) {
                return getThis();
            }
            this.mConvertedManaCost = jsonPrimitive.getAsInt();
            return getThis();
        }

        public B withPower(int power) {
            this.mPower = power;
            return getThis();
        }

        public B withPower(JsonPrimitive jsonPrimitive) {
            if (jsonPrimitive == null) {
                return getThis();
            }
            this.mPower = jsonPrimitive.getAsInt();
            return getThis();
        }

        public B withToughness(int toughness) {
            this.mToughness = toughness;
            return getThis();
        }

        public B withToughness(JsonPrimitive jsonPrimitive) {
            if (jsonPrimitive == null) {
                return getThis();
            }
            this.mToughness = jsonPrimitive.getAsInt();
            return getThis();
        }

        public B withEditions(@NonNull ArrayList<Edition> editions) {
            this.mEditions = editions;
            return getThis();
        }

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

    public static class Deserializer implements JsonDeserializer<Card> {

        @Override
        public Card deserialize(JsonElement json,
                                java.lang.reflect.Type typeOfT,
                                JsonDeserializationContext context) throws JsonParseException {
            final Builder builder = new Builder();
            final JsonObject jsonObject = json.getAsJsonObject();
            if (GsonUtils.isNonNull(jsonObject.get("name"))) {
                builder.withName(jsonObject.get("name").getAsString());
            }
            if (GsonUtils.isNonNull(jsonObject.get("id"))) {
                builder.withId(jsonObject.get("id").getAsString());
            }
            if (GsonUtils.isNonNull(jsonObject.get("url"))) {
                builder.withUrl(jsonObject.get("url").getAsString());
            }
            if (GsonUtils.isNonNull(jsonObject.get("store_url"))) {
                builder.withStoreUrl(jsonObject.get("store_url").getAsString());
            }
            if (GsonUtils.isNonNull(jsonObject.get("supertypes"))) {
                builder.withSupertypes(jsonObject.get("supertypes").getAsJsonArray());
            }
            if (GsonUtils.isNonNull(jsonObject.get("types"))) {
                builder.withTypes(jsonObject.get("types").getAsJsonArray());
            }
            if (GsonUtils.isNonNull(jsonObject.get("subtypes"))) {
                builder.withSubtypes(jsonObject.get("subtypes").getAsJsonArray());
            }
            if (GsonUtils.isNonNull(jsonObject.get("cmc"))) {
                builder.withConvertedManaCost(jsonObject.get("cmc").getAsInt());
            }
            if (GsonUtils.isNonNull(jsonObject.get("cost"))) {
                builder.withSymbols(new Symbol.CostSymbolDeserializer().deserialize(json,
                                                                                    typeOfT,
                                                                                    context));
            }
            if (GsonUtils.isNonNull(jsonObject.get("power"))) {
                builder.withPower(jsonObject.get("power").getAsInt());
            }
            if (GsonUtils.isNonNull(jsonObject.get("toughness"))) {
                builder.withToughness(jsonObject.get("toughness").getAsInt());
            }
            if (GsonUtils.isNonNull(jsonObject.get("formats"))) {
                builder.withFormats(json.getAsJsonObject().getAsJsonObject("formats"));
            }
            if (GsonUtils.isNonNull(jsonObject.get("text"))) {
                builder.withRules(new Rule.Deserializer().deserialize(json,
                                                                      typeOfT,
                                                                      context));
            }
            if (GsonUtils.isNonNull(jsonObject.get("editions"))) {
                builder.withEditions(new Edition.Deserializer().deserialize(json,
                                                                            typeOfT,
                                                                            context));
            }
            return builder.build();
        }
    }
}
