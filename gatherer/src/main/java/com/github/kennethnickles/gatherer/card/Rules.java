package com.github.kennethnickles.gatherer.card;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import com.github.kennethnickles.gatherer.util.Lists;
import com.github.kennethnickles.gatherer.util.Preconditions;
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

    @NonNull
    public static Builder builder() {
        return new Builder();
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
}
