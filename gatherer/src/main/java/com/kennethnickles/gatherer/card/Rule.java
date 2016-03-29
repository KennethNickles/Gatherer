package com.kennethnickles.gatherer.card;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.common.collect.Lists;
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

        public Builder withManaSymbols(@NonNull ArrayList<Symbol> symbols) {
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
}
