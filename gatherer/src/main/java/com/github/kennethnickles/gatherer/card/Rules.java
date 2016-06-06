package com.github.kennethnickles.gatherer.card;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.github.kennethnickles.gatherer.util.Lists;
import com.github.kennethnickles.gatherer.util.Preconditions;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.workday.postman.Postman;
import com.workday.postman.annotations.Parceled;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kenneth.nickles
 * @since 2016-06-04.
 */
@Parceled
public class Rules implements Parcelable {

    public static final Parcelable.Creator<Rules> CREATOR = Postman.getCreator(Rules.class);

    Builder mState;

    Rules() {
        // Postman
    }

    private Rules(Builder builder) {
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

    public void add(@NonNull Rule rule) {
        Preconditions.checkArgument(rule != null, "Rule");
        this.mState.mRules.add(rule);
    }

    public void addAll(@NonNull List<Rule> rules) {
        Preconditions.checkArgument(rules != null, "Rules");
        this.mState.mRules.addAll(rules);
    }

    public int size() {
        return this.mState.mRules.size();
    }

    public Rule get(int index) {
        return this.mState.mRules.get(index);
    }

    @Parceled
    public static class Builder implements Parcelable {

        public static final Creator<Builder> CREATOR = Postman.getCreator(Builder.class);

        ArrayList<Rule> mRules = Lists.newArrayList();

        Builder() {
            // Postman
        }

        @NonNull
        public Builder withRule(@NonNull Rule rule) {
            Preconditions.checkArgument(rule != null, "Rule");
            this.mRules.add(rule);
            return this;
        }

        @NonNull
        public Builder withRules(@NonNull ArrayList<Rule> rules) {
            Preconditions.checkArgument(rules != null, "Rules");
            this.mRules = rules;
            return this;
        }

        public Rules build() {
            return new Rules(this);
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

    public static class Deserializer implements JsonDeserializer<Rules> {

        @Override
        public Rules deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context)
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
            final String text = json.getAsString();
            final String[] lines = text.split("\n");
            final Builder builder = new Builder();
            for (int i = 0; i < lines.length; i++) {
                builder.withRule(Rule.from(lines[i]));
            }
            return new Rules(builder);
        }
    }
}
