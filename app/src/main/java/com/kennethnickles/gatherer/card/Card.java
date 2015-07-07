package com.kennethnickles.gatherer.card;

import com.google.common.collect.ImmutableSet;

import java.util.HashSet;
import java.util.Set;

/**
 * @author kenneth.nickles
 * @since 2015-06-28.
 */
public class Card {

    private final String name;
    private final String rules;
    private final String expansion;
    private final String format;
    private final Set<Color> manaColors;
    private final Set<Type> types ;
    private final Set<Subtype> subtypes;
    private final Cost cost;
    private final int power;
    private final int toughness;
    private final String flavorText;
    private final Mark mark;
    private final String artist;
    private final Block block;
    private final Rarity rarity;

    private Card(Builder builder) {
        this.name = builder.name;
        this.rules = builder.rules;
        this.expansion = builder.expansion;
        this.format = builder.format;
        this.manaColors = ImmutableSet.<Color>builder().addAll(Color.from(builder.colors)).build();
        this.types = ImmutableSet.<Type>builder().add(Type.from(builder.types)).build();
        this.subtypes = ImmutableSet.<Subtype>builder().addAll(Subtype.from(builder.subtypes)).build();
        this.cost = Cost.from(builder.cost);
        this.power = builder.power;
        this.toughness = builder.toughness;
        this.flavorText = builder.flavorText;
        this.mark = Mark.from(builder.mark);
        this.artist = builder.artist;
        this.block = Block.from(builder.block);
        this.rarity = Rarity.from(builder.rarity);
    }

    public static Builder builder() {
        return new Builder();
    }

    private static class Builder {

        private final Set<String> colors = new HashSet<>();
        private final Set<String> types = new HashSet<>();
        private final Set<String> subtypes = new HashSet<>();

        private String name;
        private String rules;
        private String expansion;
        private String format;
        private String cost;
        private int power;
        private int toughness;
        private String flavorText;
        private String mark;
        private String artist;
        private String block;
        private String rarity;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withRulesText(String rules) {
            this.rules = rules;
            return this;
        }

        public Builder withExpansion(Expansion expansion) {
            this.expansion = expansion.toString();
            return this;
        }

        public Builder withExpansion(String expansion) {
            this.expansion = expansion;
            return this;
        }

        public Builder withColor(Color color) {
            colors.add(color.toString());
            return this;
        }

        public Builder withColor(String manaColor) {
            this.colors.add(manaColor);
            return this;
        }

        public Builder withType(Type type) {
            this.types.add(type.toString());
            return this;
        }

        public Builder withType(String type) {
            this.types.add(type);
            return this;
        }

        public Builder withSubtype(Subtype subtype) {
            this.subtypes.add(subtype.toString());
            return this;
        }

        public Builder withSubtype(String subtype) {
            this.subtypes.add(subtype);
            return this;
        }

        public Builder withFormat(Format format) {
            this.format = format.toString();
            return this;
        }

        public Builder withFormat(String format) {
            this.format = format;
            return this;
        }

        public Builder withPower(int power) {
            this.power = power;
            return this;
        }

        public Builder withToughness(int toughness) {
            this.toughness = toughness;
            return this;
        }

        public Builder withFlavorText(String flavorText) {
            this.flavorText = flavorText;
            return this;
        }

        public Builder withMark(Mark mark) {
            this.mark = mark.toString();
            return this;
        }

        public Builder withMark(String mark) {
            this.mark = mark;
            return this;
        }

        public Builder withArtist(String artist) {
            this.artist = artist;
            return this;
        }

        public Builder withBlock(Block block) {
            this.block = block.toString();
            return this;
        }

        public Builder withBlock(String block) {
            this.block = block;
            return this;
        }

        public Builder withRarity(Rarity rarity) {
            this.rarity = rarity.toString();
            return this;
        }

        public Builder withRarity(String rarity) {
            this.rarity = rarity;
            return this;
        }

        public Card build() {
            return new Card(this);
        }
    }
}
