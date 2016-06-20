package com.github.kennethnickles.gatherer.card;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.github.kennethnickles.gatherer.util.Lists;
import com.github.kennethnickles.gatherer.util.Preconditions;
import com.workday.postman.Postman;
import com.workday.postman.annotations.Parceled;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Nullable
    public static Rule from(String lookup) {
        if (lookup == null || lookup.isEmpty()) {
            return null;
        }
        final Builder builder = builder().withRuleText(lookup);
        final String regex = "\\{(.*?)\\}";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(lookup);
        while (matcher.find()) {
            final Symbol mana = Symbol.from(lookup.substring(matcher.start(), matcher.end()));
            if (mana == null) {
                continue;
            }
            builder.withSymbol(mana);
        }
        builder.withConvertedManaCost(builder.mSymbols);
        return builder.build();
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
            final Symbol mana = Symbol.from(symbol);
            this.mSymbols.add(mana);
            return this;
        }

        @NonNull
        public Builder withSymbol(@NonNull Symbol symbol) {
            Preconditions.checkArgument(symbol != null, "Symbol");
            this.mSymbols.add(symbol);
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

        public Builder withConvertedManaCost(@NonNull List<Symbol> symbols) {
            int cmc = 0;
            for (Symbol symbol : symbols) {
                cmc += symbol.getCmc();
            }
            this.mConvertedManaCost = cmc;
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
