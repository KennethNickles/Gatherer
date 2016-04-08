package com.kennethnickles.gatherer.card;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/**
 * @author kenneth.nickles
 * @since 2016-04-04.
 */
public class CardTest {

    @Test
    public void card() throws Exception {
        final InputStream inputStream = getResourceAsStream("card.json", this);
        final JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        final GsonBuilder builder = new GsonBuilder();
        final Card.Deserializer ds = new Card.Deserializer();
        builder.registerTypeAdapter(Card.class, ds);
        final Gson gson = builder.create();
        final Card card = gson.fromJson(jsonReader, Card.class);
        Assert.assertNotNull(card);

        Assert.assertEquals("About Face", card.getName());
        Assert.assertEquals("about-face", card.getId());
        Assert.assertEquals("https://api.deckbrew.com/mtg/cards/about-face", card.getUrl());
        Assert.assertEquals(
                "http://store.tcgplayer.com/magic/urzas-legacy/about-face?partner=DECKBREW",
                card.getStoreUrl());
        Assert.assertEquals(1, card.getConvertedManaCost());
        Assert.assertEquals(1, card.getEditions().size());
        Assert.assertEquals(0, card.getPower());
        Assert.assertEquals(0, card.getToughness());

        final Edition edition = card.getEditions().get(0);
        Assert.assertEquals("Urza's Legacy", edition.getSet());
        Assert.assertEquals("ULG", edition.getSetId());
        Assert.assertEquals("https://api.deckbrew.com/mtg/sets/ULG", edition.getSetUrl());
        Assert.assertEquals("Melissa A. Benson", edition.getArtist());
        Assert.assertEquals("The overconfident are the most vulnerable.", edition.getFlavorText());
        Assert.assertEquals("https://image.deckbrew.com/mtg/multiverseid/12414.jpg",
                            edition.getImageUrl());
        Assert.assertEquals("normal", edition.getLayout());
        Assert.assertEquals(
                "http://store.tcgplayer.com/magic/urzas-legacy/about-face?partner=DECKBREW",
                edition.getStoreUrl());
        Assert.assertEquals(12414, edition.getMultiverseId());
        Assert.assertEquals(73, edition.getNumber());

        final Map<Format, Boolean> formats = card.getFormats();
        Assert.assertTrue(formats.get(Format.commander));
        Assert.assertTrue(formats.get(Format.legacy));

        final List<Supertype> supertypes = card.getSupertypes();
        Assert.assertEquals(1, supertypes.size());
        Assert.assertEquals(Supertype.legendary, supertypes.get(0));

        final List<Type> types = card.getTypes();
        Assert.assertEquals(2, types.size());
        Assert.assertEquals(Type.creature, types.get(0));
        Assert.assertEquals(Type.enchantment, types.get(1));

        final List<Subtype> subtypes = card.getSubtypes();
        Assert.assertEquals(2, types.size());
        Assert.assertEquals(Subtype.zombie, subtypes.get(0));
        Assert.assertEquals(Subtype.horror, subtypes.get(1));

        final List<Rule> rules = card.getRules();
        Assert.assertEquals(2, rules.size());
        final Rule rule0 = rules.get(0);
        Assert.assertEquals(0, rule0.getSymbols().size());
        Assert.assertEquals(0, rule0.getConvertedManaCost());
        Assert.assertEquals("Tap an untapped Cephalid you control: Tap target permanent.",
                            rule0.getRuleText());
        final Rule rule1 = rules.get(1);
        Assert.assertEquals(3, rule1.getSymbols().size());
        Assert.assertEquals(3, rule1.getConvertedManaCost());
        Assert.assertEquals("{U}{U}{U}: Tap all creatures without flying.", rule1.getRuleText());

        Assert.assertEquals(1, card.getSymbols().size());
        Assert.assertEquals(Symbol.red, card.getSymbols().get(0));
    }

    @Test
    public void cards() {
        final InputStream inputStream = getResourceAsStream("cards.json", this);
        Assert.assertNotNull(inputStream);
    }

    private static InputStream getResourceAsStream(String fileName, Object source) {
        try {
            return source.getClass().getResourceAsStream(fileName);
        } catch (Exception e) {
            return null;
        }
    }
}
