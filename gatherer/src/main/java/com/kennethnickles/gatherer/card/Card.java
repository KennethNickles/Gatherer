package com.kennethnickles.gatherer.card;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.primitives.Chars;
import com.workday.postman.Postman;
import com.workday.postman.annotations.Parceled;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kenneth.nickles
 * @since 2015-06-28.
 */
@Parceled
public class Card implements Parcelable {

    public static final Creator<Card> CREATOR = Postman.getCreator(Card.class);

    private static final String GATHERER_URL = "http://gatherer.wizards.com/";

    protected Builder mState;

    protected Card() {
        // Postman
    }

    protected Card(Builder builder) {
        this.mState = builder;
    }

    public String getName() {
        return mState.mName;
    }

    public List<Rule> getRules() {
        return mState.mRules;
    }

    public List<Type> getTypes() {
        return mState.mTypes;
    }

    public List<String> getSubtypes() {
        return mState.mSubtypes;
    }

    public String getExpansion() {
        return mState.mExpansion;
    }

    public Format getFormat() {
        return mState.mFormat;
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

    public String getFlavorText() {
        return mState.mFlavorText;
    }

    public String getMark() {
        return mState.mMark;
    }

    public String getArtist() {
        return mState.mArtist;
    }

    public String getBlock() {
        return mState.mBlock;
    }

    public Rarity getRarity() {
        return mState.mRarity;
    }

    public String getArtUrl() {
        return mState.mArtUrl;
    }

    public static Card parse(@NonNull Element element) {
        Preconditions.checkArgument(element != null, "Element");
        return new Builder().withName(parseName(element))
                            .withRules(parseRules(element))
                            .withExpansion(parseExpansion(element))
                            .withFormat(parseFormat(element))
                            .withTypes(parseTypes(element))
                            .withSubtypes(parseSubtypes(element))
                            .withSymbols(parseManaSymbols(element))
                            .withConvertedManaCost(parseConvertedManaCost(element))
                            .withPower(parsePower(element))
                            .withToughness(parseToughness(element))
                            .withFlavorText(parseFlavorText(element))
                            .withMark(parseMark(element))
                            .withArtist(parseArtist(element))
                            .withBlock(parseBlock(element))
                            .withRarity(parseRarity(element))
                            .withArtUrl(parseArtUrl(element))
                            .build();
    }

    private static String parseName(Element element) {
        return element.getElementsByClass("cardTitle").first().getElementsByTag("a").text();
    }

    private static ArrayList<Rule> parseRules(Element element) {
        final Element typeLine = element.getElementsByClass("rulesText").first();
        final Elements ruleElements = typeLine.getElementsByTag("p");
        final ArrayList<Rule> rules = Lists.newArrayList();
        for (Element ruleElement : ruleElements) {
            final ArrayList<Symbol> symbols = Lists.newArrayList();
            for (Element manaSymbol : ruleElement.getElementsByTag("img")) {
                final String alt = manaSymbol.attr("alt");
                symbols.add(Symbol.from(alt));
            }
            rules.add(Rule.builder()
                          .withManaSymbols(symbols)
                          .withConvertedManaCost(0)
                          .withRuleText(ruleElement.text())
                          .build());
        }
        return rules;
    }

    private static String parseExpansion(Element element) {
        final String title = element.getElementsByClass("setVersions")
                                    .first()
                                    .getElementsByTag("img")
                                    .attr("title");
        return title.substring(0, title.indexOf(' '));
    }

    private static String parseFormat(Element element) {
        return null;
    }

    private static ArrayList<Type> parseTypes(Element element) {
        final String typeString = element.getElementsByClass("typeLine").first().text();
        final String[] typesStrings = typeString.substring(0, typeString.length()).split(" ");
        final ArrayList<Type> types = Lists.newArrayList();
        for (String type : typesStrings) {
            if (Chars.compare(type.charAt(0), (char) 8212) == 0) {
                return types;
            }
            final Type temp = Type.from(type);
            if (temp == null) {
                continue;
            }
            types.add(temp);
        }
        return types;
    }

    private static ArrayList<String> parseSubtypes(Element element) {
        final String subtypeString = element.getElementsByClass("typeLine").first().text();
        final String[] subtypeStrings =
                subtypeString.substring(0, subtypeString.length()).split(" ");
        final ArrayList<String> subtypes = Lists.newArrayList();
        boolean isHyphenFound = false;
        for (String subtype : subtypeStrings) {
            if (Chars.compare(subtype.charAt(0), (char) 8212) == 0) {
                isHyphenFound = true;
                continue;
            }
            if (isPowerToughness(subtype)) {
                continue;
            }
            if (isHyphenFound) {
                subtypes.add(subtype);
            }
        }
        return subtypes;
    }

    private static boolean isPowerToughness(String string) {
        if (Chars.compare(string.charAt(0), (char) 40) != 0) {
            return false;
        }
        if (!Character.isDigit(string.charAt(1))) {
            return false;
        }
        if (Chars.compare(string.charAt(2), (char) 47) != 0) {
            return false;
        }
        if (!Character.isDigit(string.charAt(3))) {
            return false;
        }
        if (Chars.compare(string.charAt(4), (char) 41) != 0) {
            return false;
        }
        return true;
    }

    private static ArrayList<Symbol> parseManaSymbols(Element element) {
        final Elements images =
                element.getElementsByClass("manaCost").first().getElementsByTag("img");
        final ArrayList<Symbol> symbols = Lists.newArrayList();
        for (Element image : images) {
            symbols.add(Symbol.from(image.attr("alt")));
        }
        return symbols;
    }

    private static int parseConvertedManaCost(Element element) {
        final String convertedManaCost =
                element.getElementsByClass("convertedManaCost").first().text();
        return Integer.valueOf(convertedManaCost);
    }

    private static int parsePower(Element element) {
        final String typeString = element.getElementsByClass("typeLine").first().text();
        final String[] typesStrings = typeString.substring(0, typeString.length()).split(" ");
        for (String type : typesStrings) {
            if (isPowerToughness(type)) {
                return Integer.valueOf(Character.toString(type.charAt(1)));
            }
        }
        return -1;
    }

    private static int parseToughness(Element element) {
        final String typeString = element.getElementsByClass("typeLine").first().text();
        final String[] typesStrings = typeString.substring(0, typeString.length()).split(" ");
        for (String type : typesStrings) {
            if (isPowerToughness(type)) {
                return Integer.valueOf(Character.toString(type.charAt(3)));
            }
        }
        return -1;
    }

    private static String parseFlavorText(Element element) {
        return null;
    }

    private static String parseMark(Element element) {
        return null;
    }

    private static String parseArtist(Element element) {
        return null;
    }

    private static String parseBlock(Element element) {
        return null;
    }

    private static String parseRarity(Element element) {
        final String title = element.getElementsByClass("setVersions")
                                    .first()
                                    .getElementsByTag("img")
                                    .attr("title");
        final int leftParen = title.indexOf('(');
        final int rightParen = title.indexOf(')');
        final String rarity = title.substring(leftParen + 1, rightParen);
        return rarity;
    }

    private static String parseArtUrl(Element element) {
        return GATHERER_URL + element.getElementsByTag("img")
                                     .first()
                                     .attr("src")
                                     .replace("../", "");
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

        ArrayList<Symbol> mSymbols = Lists.newArrayList();
        ArrayList<Type> mTypes = Lists.newArrayList();
        ArrayList<String> mSubtypes = Lists.newArrayList();
        ArrayList<Rule> mRules = Lists.newArrayList();
        String mName;
        String mExpansion;
        Format mFormat;
        int mConvertedManaCost;
        int mPower;
        int mToughness;
        String mFlavorText;
        String mMark;
        String mArtist;
        String mBlock;
        Rarity mRarity;
        String mArtUrl;

        protected Builder() {
        }

        @NonNull
        public B withName(String name) {
            this.mName = name;
            return getThis();
        }

        public B withRule(ArrayList<Symbol> symbols, int convertedManaCost, String ruleText) {
            this.mRules.add(Rule.builder()
                                .withManaSymbols(symbols)
                                .withConvertedManaCost(convertedManaCost)
                                .withRuleText(ruleText)
                                .build());
            return getThis();
        }

        public B withRules(ArrayList<Rule> rules) {
            this.mRules = rules;
            return getThis();
        }

        public B withExpansion(String expansion) {
            this.mExpansion = expansion;
            return getThis();
        }

        public B withTypes(ArrayList<Type> types) {
            this.mTypes = types;
            return getThis();
        }

        public B withSubtypes(ArrayList<String> subtypes) {
            this.mSubtypes = subtypes;
            return getThis();
        }

        public B withFormat(String format) {
            this.mFormat = Format.from(format);
            return getThis();
        }

        public B withFormat(Format format) {
            this.mFormat = format;
            return getThis();
        }

        public B withSymbols(ArrayList<Symbol> symbols) {
            this.mSymbols = symbols;
            return getThis();
        }

        public B withConvertedManaCost(int convertedManaCost) {
            this.mConvertedManaCost = convertedManaCost;
            return getThis();
        }

        public B withPower(int power) {
            this.mPower = power;
            return getThis();
        }

        public B withToughness(int toughness) {
            this.mToughness = toughness;
            return getThis();
        }

        public B withFlavorText(String flavorText) {
            this.mFlavorText = flavorText;
            return getThis();
        }

        public B withMark(String mark) {
            this.mMark = mark;
            return getThis();
        }

        public B withArtist(String artist) {
            this.mArtist = artist;
            return getThis();
        }

        public B withBlock(String block) {
            this.mBlock = block;
            return getThis();
        }

        public B withRarity(Rarity rarity) {
            this.mRarity = rarity;
            return getThis();
        }

        public B withRarity(String rarity) {
            this.mRarity = Rarity.from(rarity);
            return getThis();
        }

        public B withArtUrl(String artUrl) {
            this.mArtUrl = artUrl;
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
