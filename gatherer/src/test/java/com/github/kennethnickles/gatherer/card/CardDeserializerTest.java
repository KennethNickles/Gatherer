package com.github.kennethnickles.gatherer.card;

import android.util.Log;
import com.github.kennethnickles.gatherer.util.CardGsonFactory;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * @author kenneth.nickles
 * @since 2016-04-04.
 */
public class CardDeserializerTest {

	@org.junit.Rule
	public MockitoRule rule = MockitoJUnit.rule();

	@Mock
	Log log;

	@Test
	public void card() throws Exception {
		final InputStream inputStream = getResourceAsStream("card.json", this);
		final JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
		final Gson gson = new CardGsonFactory().createCardGson();
		final Card card = gson.fromJson(jsonReader, Card.class);
		Assert.assertNotNull(card);

		Assert.assertEquals("About Face", card.getName());
		Assert.assertEquals("about-face", card.getId());
		Assert.assertEquals("https://api.deckbrew.com/mtg/cards/about-face", card.getUrl());
		Assert.assertEquals("http://store.tcgplayer.com/magic/urzas-legacy/about-face?partner=DECKBREW",
							card.getStoreUrl());
		Assert.assertEquals(1, card.getConvertedManaCost());
		Assert.assertEquals(1, card.getEditions().size());
		Assert.assertNull(card.getPower());
		Assert.assertNull(card.getToughness());
		// Test asterixes

		final Edition edition = card.getEditions().get(0);
		Assert.assertEquals("Urza's Legacy", edition.getSet());
		Assert.assertEquals("ULG", edition.getSetId());
		Assert.assertEquals("https://api.deckbrew.com/mtg/sets/ULG", edition.getSetUrl());
		Assert.assertEquals("Melissa A. Benson", edition.getArtist());
		Assert.assertEquals("The overconfident are the most vulnerable.", edition.getFlavorText());
		Assert.assertEquals("https://image.deckbrew.com/mtg/multiverseid/12414.jpg", edition.getImageUrl());
		Assert.assertEquals("normal", edition.getLayout());
		Assert.assertEquals("http://store.tcgplayer.com/magic/urzas-legacy/about-face?partner=DECKBREW",
							edition.getStoreUrl());
		Assert.assertEquals(12414, edition.getMultiverseId());
		Assert.assertEquals("73", edition.getNumber());

		final Map<Format, Boolean> formats = card.getFormats();
		Assert.assertTrue(formats.get(Format.COMMANDER));
		Assert.assertTrue(formats.get(Format.LEGACY));

		final List<Supertype> supertypes = card.getSupertypes();
		Assert.assertEquals(1, supertypes.size());
		Assert.assertEquals(Supertype.LEGENDARY, supertypes.get(0));

		final List<Type> types = card.getTypes();
		Assert.assertEquals(2, types.size());
		Assert.assertEquals(Type.CREATURE, types.get(0));
		Assert.assertEquals(Type.ENCHANTMENT, types.get(1));

		final List<String> subtypes = card.getSubtypes();
		Assert.assertEquals(2, types.size());
		Assert.assertEquals("zombie", subtypes.get(0));
		Assert.assertEquals("horror", subtypes.get(1));

		final List<Oracle> oracleTexts = card.getOracleTexts();
		Assert.assertEquals(2, oracleTexts.size());
		final Oracle oracle0 = oracleTexts.get(0);
		Assert.assertEquals(0, oracle0.getSymbols().size());
		Assert.assertEquals(0, oracle0.getConvertedManaCost());
		Assert.assertEquals("Tap an untapped Cephalid you control: Tap target permanent.", oracle0.getOracleText());
		final Oracle oracle1 = oracleTexts.get(1);
		Assert.assertEquals(3, oracle1.getSymbols().size());
		Assert.assertEquals(3, oracle1.getConvertedManaCost());
		Assert.assertEquals("{U}{U}{U}: Tap all creatures without flying.", oracle1.getOracleText());

		Assert.assertEquals(1, card.getSymbols().size());
		Assert.assertEquals(Symbol.RED, card.getSymbols().get(0));
	}

	private static InputStream getResourceAsStream(String fileName, Object source) {
		try {
			return source.getClass().getResourceAsStream(fileName);
		} catch (Exception e) {
			return null;
		}
	}
}
